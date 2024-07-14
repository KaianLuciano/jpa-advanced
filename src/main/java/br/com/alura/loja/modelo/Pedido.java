package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal;
    private LocalDate data = LocalDate.now();
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Long id, BigDecimal valorTotal, LocalDate data, Cliente cliente) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.data = data;
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getId(), pedido.getId()) && Objects.equals(getValorTotal(), pedido.getValorTotal()) && Objects.equals(getData(), pedido.getData()) && Objects.equals(getCliente(), pedido.getCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValorTotal(), getData(), getCliente());
    }
}
