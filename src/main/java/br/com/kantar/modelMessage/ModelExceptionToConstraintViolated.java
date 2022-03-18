/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.modelMessage;

/**
 *
 * @author eduardo
 */
public class ModelExceptionToConstraintViolated {

private String CampoIrregular;
private String Mensagem;
private int codigo;

    public ModelExceptionToConstraintViolated(String CampoIrregular, String Mensagem, int codigo) {
        this.CampoIrregular = CampoIrregular;
        this.Mensagem = Mensagem;
        this.codigo = codigo;
    }

    public String getCampoIrregular() {
        return CampoIrregular;
    }

    public void setCampoIrregular(String CampoIrregular) {
        this.CampoIrregular = CampoIrregular;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String Mensagem) {
        this.Mensagem = Mensagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "ModelExceptionToConstraintViolated{" + "CampoIrregular=" + CampoIrregular + ", Mensagem=" + Mensagem + ", codigo=" + codigo + '}';
    }

 




}

