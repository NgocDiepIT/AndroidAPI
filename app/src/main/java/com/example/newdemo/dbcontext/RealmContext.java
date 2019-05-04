package com.example.newdemo.dbcontext;

import com.example.newdemo.json_models.response.UserInfor;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmContext {
    private Realm realm;
    private static RealmContext instance;

    private RealmContext() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmContext getInstance() {
        if (instance == null) {
            instance = new RealmContext();
        }
        return instance;
    }

    public void addUser(UserInfor userInfor) {
        deleteAllUser();

        realm.beginTransaction();

        UserInfor user = realm.createObject(UserInfor.class);
        user.setUserId(userInfor.getUserId());
        user.setFullName(userInfor.getFullName());
        user.setAvatar(userInfor.getAvatar());
        realm.copyFromRealm(user);

        realm.commitTransaction();
    }

    public UserInfor getUser() {
        return realm.where(UserInfor.class).findFirst();
    }

    public void deleteAllUser() {
        RealmResults<UserInfor> userInforList = realm.where(UserInfor.class).findAll();

        realm.beginTransaction();
        userInforList.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
