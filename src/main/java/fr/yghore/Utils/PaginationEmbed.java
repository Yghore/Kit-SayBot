package fr.yghore.Utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PaginationEmbed
{

    private EmbedBuilder messageEmbed;
    private int nbFieldsPerPage;

    public PaginationEmbed(EmbedBuilder messageEmbed, int nbFieldsPerPage) {
        this.messageEmbed = messageEmbed;
        this.nbFieldsPerPage = nbFieldsPerPage;
    }

    public int getNbFieldsPerPage() {
        return nbFieldsPerPage;
    }

    public void setNbFieldsPerPage(int nbFieldsPerPage) {
        this.nbFieldsPerPage = nbFieldsPerPage;
    }



    public MessageEmbed getPaginateEmbed(int page)
    {
        List<MessageEmbed.Field> fields = this.messageEmbed.getFields().stream().filter(field -> !field.getName().equals("\u200e")).collect(Collectors.toList());

        int actualField = ((page - 1) * this.nbFieldsPerPage);
        int endField = actualField + this.nbFieldsPerPage - 1;
        int totalPage = fields.size()  / this.getNbFieldsPerPage();
        if(page > totalPage){return Const.EMBED_MISSING_PAGE;}
        if(page == -42){return new EmbedBuilder().setAuthor(":O").setDescription("La réponse de la vie !").setColor(Color.green).build();}
        if(page < 0){return Const.EMBED_MISSING_PAGE;}
        if(page == 0){return this.messageEmbed.build();}

        EmbedBuilder eb = new EmbedBuilder(this.messageEmbed);
        eb.clearFields();


        if(actualField >= fields.size()){return null;}


        for (int i = actualField; i <= endField; i++)
        {
            eb.addField(fields.get(i));
        }


        eb.setFooter("" + page + "/" + totalPage);

        return eb.build();
    }



}
