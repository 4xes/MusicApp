package ru.petrushin.ya.music.data.cashe.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ExpiredRealm extends RealmObject {
  @PrimaryKey private String key;
  private long updatedTime;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public long getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(long updatedTime) {
    this.updatedTime = updatedTime;
  }
}
