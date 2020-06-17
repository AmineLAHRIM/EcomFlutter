package com.ecomflutter.demo.service.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Response {

    private List<Message> infos;
    private List<Message> warnigs;
    private List<Message> errors;
    private boolean succes=false;
    private Object output;
    private List<Object> inputs;
    private Map<String, Object> details;

    public Message constractInfo(String libelle, int code) {
        return new Message(code, libelle, TypeMessage.Info);
    }

    public Message constractError(String libelle, int code) {
        return new Message(code, libelle, TypeMessage.Error);
    }

    public Message constractWarning(String libelle, int code) {
        return new Message(code, libelle, TypeMessage.Warning);
    }

    public void addInfo(String info, int code) {
        getInfos().add(constractInfo(info, code));
    }

    public void addWarrnig(String warrnig, int code) {
        getWarrnigs().add(constractWarning(warrnig, code));
    }

    public void addError(String error, int code) {
        getErrors().add(constractError(error, code));
    }

    public boolean hasInfos() {
        return !getInfos().isEmpty();
    }

    public boolean hasWarrnigs() {
        return !getWarrnigs().isEmpty();
    }

    public boolean hasErrors() {
        return !getErrors().isEmpty();
    }



    public List<Message> getInfos() {
        if (infos == null) {
            infos = new ArrayList<>();
        }
        return infos;
    }

    public void setInfos(List<Message> infos) {
        this.infos = infos;
    }

    public List<Message> getWarrnigs() {
        if (warnigs == null) {
            warnigs = new ArrayList<>();
        }
        return warnigs;
    }

    public void setWarrnigs(List<Message> warrnigs) {
        this.warnigs = warrnigs;
    }

    public List<Message> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public void setErrors(List<Message> errors) {
        this.errors = errors;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public List<Object> getInputs() {
        return inputs;
    }

    public void setInputs(List<Object> inputs) {
        this.inputs = inputs;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public List<Message> getWarnigs() {
        return warnigs;
    }

    public void setWarnigs(List<Message> warnigs) {
        this.warnigs = warnigs;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }
}
