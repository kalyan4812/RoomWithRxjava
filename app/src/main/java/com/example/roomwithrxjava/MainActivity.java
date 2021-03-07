package com.example.roomwithrxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomwithrxjava.db.MyDatabase;
import com.example.roomwithrxjava.db.User;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    EditText name, email, password;
    public MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.ed1);
        name = findViewById(R.id.ed3);
        password = findViewById(R.id.ed2);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "userdb").build();


    }

    public void insert(View view) {
        final String uemail = email.getText().toString();
        String uname = name.getText().toString();
        String upassword = password.getText().toString();
        User user = new User(uemail, uname, upassword);
        myDatabase.myDao().insertUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(), uemail + "was added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void update(View view) {
        final String uemail = email.getText().toString();
        String uname = name.getText().toString();
        String upassword = password.getText().toString();
        User user = new User(uemail, uname, upassword);
        myDatabase.myDao().updateUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Toast.makeText(getApplicationContext(), "effected rows :" + integer, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }

    public void delete(View view) {
        final String uemail = email.getText().toString();
        User user = new User();
        user.setUseremail(uemail);
        myDatabase.myDao().deleteUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Toast.makeText(getApplicationContext(), "effected rows :" + integer, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    public void read(View view) {
        myDatabase.myDao().getUserByEmail(email.getText().toString()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                Toast.makeText(getApplicationContext(), user.getUsername() + "\n" + user.getUserpassword() + "\n" +
                        user.getUseremail(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
