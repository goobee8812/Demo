package com.example.goobee_yuer.demo.bean;

/**
 * Created by Goobee_yuer on 2018/3/9.
 */

public class Machine {
    private String machineName;
    private String machineRun;
    private Boolean machineConn;

    public Machine(String machineName, String machineRun, Boolean machineConn) {
        this.machineName = machineName;
        this.machineRun = machineRun;
        this.machineConn = machineConn;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getMachineRun() {
        return machineRun;
    }

    public Boolean getMachineConn() {
        return machineConn;
    }
}
