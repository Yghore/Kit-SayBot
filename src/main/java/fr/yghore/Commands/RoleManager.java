package fr.yghore.Commands;

import fr.yghore.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoleManager extends ListenerAdapter {

    private static List<Permission> FORBIDDEN_PERMISSION_FOR_GIVEN_ROLE = new ArrayList<>(List.of(
            Permission.MANAGE_ROLES,
            Permission.MANAGE_PERMISSIONS,
            Permission.ADMINISTRATOR,
            Permission.BAN_MEMBERS
    ));

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("role"))
        {
           Role role = event.getOption("role").getAsRole();
           if(role.getPermissions().stream().anyMatch(FORBIDDEN_PERMISSION_FOR_GIVEN_ROLE::contains))
           {
               event.reply("Le role choisi semble avoir trop de permission, merci de vérifier !").setEphemeral(true).queue();
               return;
           }
           String title = event.getOption("name").getAsString();
           event.replyEmbeds(new EmbedBuilder().setAuthor(title).setDescription("Bouton pour le role " + role.getName() ).setColor(Color.GREEN).build())
                   .addActionRow(Button.primary(role.getId(), role.getName())).queue();


        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        System.out.println(event.getComponentId());
        List<String> roles = Main.guild.getRoles().stream().map(Role::getId).toList();
        System.out.println(roles);
        if (roles.contains(event.getComponentId())) {
            Role role = Main.guild.getRoleById(event.getComponentId());
            if(role.getPermissions().stream().anyMatch(FORBIDDEN_PERMISSION_FOR_GIVEN_ROLE::contains))
            {
                event.reply("Une erreur est survenue, si elle persiste merci de contacter un administrateur.").setEphemeral(true).queue();
                return;
            }
                if(event.getMember().getRoles().contains(role))
            {
                Main.guild.removeRoleFromMember(event.getMember().getUser(), role).queue();
                event.reply("Le role " + role.getName() + " vous à bien été enlevé!").setEphemeral(true).queue();
                return;

            }
            Main.guild.addRoleToMember(event.getMember().getUser(), role).queue();
            event.reply("Le role " + role.getName() + " vous à bien été ajouté!").setEphemeral(true).queue();
        }
    }
}