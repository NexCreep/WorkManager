package me.nexcreep.workmanager.commands;

public class RandomNum {

    public int setAmount(int MA){
        return (int)Math.floor((Math.random()*MA)+1);
    }

    public int setMaterial(int HML){
        return (int)Math.floor((Math.random()*HML)+1);
    }
}
