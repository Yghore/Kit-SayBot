package fr.yghore.Utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Const
{

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static final MessageEmbed EMBED_NOT_FOUND = new EmbedBuilder().setAuthor("Introuvable").setDescription("Cette donnée est introuvable !").setColor(Color.RED).build();


    public static final MessageEmbed EMBED_FORBIDDEN_PERMISSION = new EmbedBuilder().setAuthor("Permission").setDescription("Vous n'avez pas la permission de faire cette action !").setColor(Color.RED).build();

    public static final MessageEmbed EMBED_MISSING_OPTION = new EmbedBuilder().setAuthor("Option manquante").setDescription("Il manque une option !").setColor(Color.RED).build();

    public static final MessageEmbed EMBED_MISSING_PAGE = new EmbedBuilder().setAuthor("Erreur").setDescription("La page n'existe pas !").setColor(Color.RED).build();


}
