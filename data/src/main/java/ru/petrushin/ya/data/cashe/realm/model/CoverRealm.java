package ru.petrushin.ya.data.cashe.realm.model;

import io.realm.RealmObject;

public class CoverRealm extends RealmObject {
  private String small;
  private String big;

  public String getSmall() {
    return small;
  }

  public void setSmall(String small) {
    this.small = small;
  }

  public String getBig() {
    return big;
  }

  public void setBig(String big) {
    this.big = big;
  }
}
