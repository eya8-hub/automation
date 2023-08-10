package org.example.models;

public class Step {
    public int step;
    public Action action;

    public Step(int step) {
        this.step = step;
    }

    public Step(int step, Action action) {
        this.step = step;
        this.action = action;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
