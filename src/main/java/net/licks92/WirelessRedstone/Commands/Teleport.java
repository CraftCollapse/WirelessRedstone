package net.licks92.WirelessRedstone.Commands;

import net.licks92.WirelessRedstone.Signs.WirelessChannel;
import net.licks92.WirelessRedstone.WirelessRedstone;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(description = "Teleport to specific sign", usage = "<channel> <type> <signID>", aliases = {"teleport", "tp"},
        permission = "teleport", canUseInConsole = false, canUseInCommandBlock = false)
public class Teleport extends WirelessCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 3) {
            WirelessRedstone.getUtils().sendFeedback(WirelessRedstone.getStrings().commandTooFewArguments, sender, true);
            return;
        }

        WirelessChannel channel = WirelessRedstone.getStorage().getWirelessChannel(args[0]);
        if (channel == null) {
            WirelessRedstone.getUtils().sendFeedback(WirelessRedstone.getStrings().channelNotFound, sender, true);
            return;
        }

        if (!hasAccessToChannel(sender, args[0])) {
            WirelessRedstone.getUtils().sendFeedback(WirelessRedstone.getStrings().permissionChannelAccess, sender, true);
            return;
        }

        Integer index = 0;

        try {
            index = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            WirelessRedstone.getUtils().sendFeedback(WirelessRedstone.getStrings().commandNoNumber, sender, true);
            return;
        }

        Player player = (Player) sender;

        switch (args[1].toUpperCase()) {
            case "TRANSMITTER":
            case "TRANSMITTERS":
            case "T":
                try {
                    Location locTransmitter = channel.getTransmitters().get(index).getLocation().add(0.5, 0, 0.5);
                    locTransmitter.setYaw(player.getLocation().getYaw());
                    locTransmitter.setPitch(player.getLocation().getPitch());
                    player.teleport(locTransmitter);
                } catch (IndexOutOfBoundsException ex) {
                    WirelessRedstone.getUtils().sendFeedback(WirelessRedstone.getStrings().commandSignNotFound, player, true);
                }
                break;
            case "RECEIVER":
            case "RECEIVERS":
            case "R":
                try {
                    Location locReceiver = channel.getReceivers().get(index).getLocation().add(0.5, 0, 0.5);
                    locReceiver.setYaw(player.getLocation().getYaw());
                    locReceiver.setPitch(player.getLocation().getPitch());
                    player.teleport(locReceiver);
                } catch (IndexOutOfBoundsException ex) {
                    WirelessRedstone.getUtils().sendFeedback(WirelessRedstone.getStrings().commandSignNotFound, player, true);
                }
                break;
            case "SCREEN":
            case "SCREENS":
            case "S":
                try {
                    Location locScreen = channel.getScreens().get(index).getLocation().add(0.5, 0, 0.5);
                    locScreen.setYaw(player.getLocation().getYaw());
                    locScreen.setPitch(player.getLocation().getPitch());
                    player.teleport(locScreen);
                } catch (IndexOutOfBoundsException ex) {
                    WirelessRedstone.getUtils().sendFeedback(WirelessRedstone.getStrings().commandSignNotFound, player, true);
                }
                break;
        }
    }
}
