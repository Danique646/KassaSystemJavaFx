package com.kjellvos.school.databaseConnectivityTest;

/**
 * Created by kjevo on 2/21/17.
 */
public class ValuesForDatabaseConnectivityTest {
    private int id, value;

    public ValuesForDatabaseConnectivityTest(int id, int value){
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
