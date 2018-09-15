package com.gosemathraj.assignment.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gosemathraj.assignment.App;
import com.gosemathraj.assignment.R;
import com.gosemathraj.assignment.adapters.UserAdapter;
import com.gosemathraj.assignment.di.component.DaggerHomeComponent;
import com.gosemathraj.assignment.di.module.HomeModule;
import com.gosemathraj.assignment.models.User;
import com.gosemathraj.assignment.presenter.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iamsparsh on 15/9/18.
 */

public class HomeActivity1 extends AppCompatActivity implements BaseView,UserAdapter.UserActions{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.user_recyclerview)
    RecyclerView userRecyclerview;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    BasePresenterImpl basePresenterImpl;

    private Dialog addUserDialog,editdeleteUserDialog;
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        DaggerHomeComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);

        try{
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() {
        initToolbar();
        initVariables();
        getUserList();
    }

    private void getUserList() {
        basePresenterImpl.loadUsers();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void initVariables() {
        userAdapter = new UserAdapter(HomeActivity1.this,userList);
        userRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerview.setAdapter(userAdapter);
    }

    @Override
    public void showProgressbar() {
        if(progressBar != null){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void closeProgressbar() {
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showusers(List<User> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
        userAdapter.notifyDataSetChanged();

        basePresenterImpl.insertAllUsers(this.userList);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(HomeActivity1.this,"Server Error",Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateList() {
        userList.clear();
        userList.addAll(basePresenterImpl.getAllUsers());
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_user) {
            openAddUserDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openAddUserDialog() {
        addUserDialog = new Dialog(HomeActivity1.this);
        addUserDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) HomeActivity1.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_add_user,null,false);

        final EditText firstNameEdit = dialogView.findViewById(R.id.first_name_edit);
        final EditText lastNameEdit = dialogView.findViewById(R.id.last_name_edit);
        final Button submitBtn = dialogView.findViewById(R.id.submit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserDialog.dismiss();
                User user = new User();
                user.setFirstName(firstNameEdit.getText().toString());
                user.setLastName(lastNameEdit.getText().toString());
                user.setAvatar("");
                basePresenterImpl.addUser(user);
            }
        });

        addUserDialog.setContentView(dialogView);
        addUserDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addUserDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        addUserDialog.setCancelable(true);
        addUserDialog.setCanceledOnTouchOutside(true);
        addUserDialog.show();
    }

    @Override
    public void onUserClicked(User user) {
        openEditDeleteUserDialog(user);
    }

    private void openEditDeleteUserDialog(final User user) {
        editdeleteUserDialog = new Dialog(HomeActivity1.this);
        editdeleteUserDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) HomeActivity1.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_options,null,false);

        final LinearLayout editDeleteOptionsLinear = dialogView.findViewById(R.id.edit_delete_options_linear);
        final LinearLayout editOptionsLinear = dialogView.findViewById(R.id.edit_options_linear);

        final TextView editUser = dialogView.findViewById(R.id.edit_user);
        final TextView deleteUser = dialogView.findViewById(R.id.delete_user);

        final EditText firstNameEdit = dialogView.findViewById(R.id.first_name_edit);
        final EditText lastNameEdit = dialogView.findViewById(R.id.last_name_edit);
        final Button submitBtn = dialogView.findViewById(R.id.submit_btn);

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDeleteOptionsLinear.setVisibility(View.GONE);
                editOptionsLinear.setVisibility(View.VISIBLE);

                firstNameEdit.setText(user.getFirstName());
                lastNameEdit.setText(user.getLastName());

            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basePresenterImpl.deleteUser(user);
                editdeleteUserDialog.dismiss();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editdeleteUserDialog.dismiss();
                user.setFirstName(firstNameEdit.getText().toString());
                user.setLastName(lastNameEdit.getText().toString());
                basePresenterImpl.updateUser(user);
            }
        });

        editdeleteUserDialog.setContentView(dialogView);
        editdeleteUserDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editdeleteUserDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        editdeleteUserDialog.setCancelable(true);
        editdeleteUserDialog.setCanceledOnTouchOutside(true);
        editdeleteUserDialog.show();
    }
}
