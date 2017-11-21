package javafxmvc.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Venda implements Serializable {

    private int cdVenda;
    private LocalDate data;
    private double valor;
    private boolean pago;
    private List<ItemDeVenda> itemDeVenda;
    private Cliente cliente;

    public Venda() {
        
    }

    public Venda(int cdVenda, LocalDate data, double valor, boolean pago, List<ItemDeVenda> itemDeVenda, Cliente cliente) {
        this.cdVenda = cdVenda;
        this.data = data;
        this.valor = valor;
        this.pago = pago;
        this.itemDeVenda = itemDeVenda;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(int cdVenda) {
        this.cdVenda = cdVenda;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public List<ItemDeVenda> getItemDeVenda() {
        return itemDeVenda;
    }

    public void setItemDeVenda(List<ItemDeVenda> itemDeVenda) {
        this.itemDeVenda = itemDeVenda;
    }
}
