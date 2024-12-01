package upt.proj.condominio.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Mensagens")
public class Mensagens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Sender", nullable = false)
    private Conta sender;

    @ManyToOne
    @JoinColumn(name = "Id_Recipient", nullable = false)
    private Conta recipient;

    @Column(name = "Mensagem", nullable = false)
    private String mensagem;

    @Column(name = "Lidas", nullable = false)
    private Boolean read;


    public Mensagens() {
        //JPA constructor
    }

    public Mensagens(Conta sender, Conta recipient, String mensagem, Boolean read) {
        this.sender = sender;
        this.recipient = recipient;
        this.mensagem = mensagem;
        this.read = read;
    }

    public Integer getId() {
        return id;
    }

    public Conta getSender() {
        return sender;
    }

    public Conta getRecipient() {
        return recipient;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setSender(Conta sender) {
        this.sender = sender;
    }

    public void setRecipient(Conta recipient) {
        this.recipient = recipient;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
    

}
