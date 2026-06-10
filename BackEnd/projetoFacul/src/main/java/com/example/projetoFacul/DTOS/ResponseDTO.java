package com.example.projetoFacul.DTOS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("codigo_erro")
    private String codigoErro;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("timestamp")
    private String timestamp;

    public ResponseDTO() {}

    // Construtor para erro
    public ResponseDTO(boolean status, String codigoErro, String mensagem, String timestamp) {
        this.status = status;
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
        this.timestamp = timestamp;
    }

    // Construtor para sucesso
    public ResponseDTO(boolean status, String mensagem, String timestamp) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = timestamp;
    }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getCodigoErro() { return codigoErro; }
    public void setCodigoErro(String codigoErro) { this.codigoErro = codigoErro; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}