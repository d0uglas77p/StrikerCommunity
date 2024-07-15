package application.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class FeedBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_LENGTH = 1507;
    private static final Logger LOGGER = Logger.getLogger(FeedBean.class.getName());
    private LocalDate dataPublicacao;
    private String mensagem;
    private List<String> postagens = new ArrayList<>();

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<String> postagens) {
        this.postagens = postagens;
    }

    public void publicar() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (mensagem != null && !mensagem.trim().isEmpty()) {
            // Verifica o comprimento total da mensagem, incluindo texto e tags de imagem
            String texto = extractText(mensagem); // Extraí o texto da mensagem
            if (texto.length() > MAX_LENGTH) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro", "Limite de caracteres atingido: 1500"));
                LOGGER.info("LIMITE DE CARACTERES ALCANÇADO: " + MAX_LENGTH);
                return; // Impede a publicação se o texto exceder o limite
            }

            // Captura a data da publicação
            LocalDate dataAtual = LocalDate.now();
            dataPublicacao = dataAtual;

            // Adiciona a postagem (texto e imagem, se houver)
            postagens.add(0, mensagem);
            mensagem = null;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso", "Publicação realizada com sucesso!"));
            LOGGER.info("PUBLICADO COM SUCESSO");

        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso", "O campo de publicação não pode estar vazio."));
            mensagem = null;
            LOGGER.info("NENHUMA MENSAGEM ENCONTRADA");
        }
    }

    // Método para extrair o texto da mensagem, ignorando tags de imagem
    private String extractText(String mensagem) {
        // Remove as tags de imagem para contar apenas o texto
        return mensagem.replaceAll("<img[^>]*>", ""); // Remove tags <img>
    }

    public void atualizarFeed() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Feed Atualizado", "O feed de postagens foi atualizado!"));
        LOGGER.info("FEED ATUALIZADO");
    }

    public String formatarDataPublicacao() {
        if (dataPublicacao != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return dataPublicacao.format(formatter);
        }
        return "";
    }

    // TESTE ----> RETIRAR DEPOIS <-----
    public void limparPostagens() {
        postagens.clear(); // Limpa todas as postagens
    }
}
