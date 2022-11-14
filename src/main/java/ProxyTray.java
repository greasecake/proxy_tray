import registry.RegistryService;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class ProxyTray {
    private Boolean lastStatus = null;
    private final SystemTray tray;

    public ProxyTray() {
        tray = SystemTray.getSystemTray();
    }

    public void run() throws Exception {
        while (true) {
            updateStatus();
            Thread.sleep(5000);
        }
    }

    public void updateStatus() {
        RegistryService service = new RegistryService();
        boolean proxyStatus = service.getValue();

        if (lastStatus != null && proxyStatus == lastStatus) {
            return;
        }

        PopupMenu menu = new PopupMenu();

        MenuItem toggleButton = new MenuItem(String.format("%s proxy", proxyStatus ? "Disable" : "Enable"));
        toggleButton.addActionListener(e -> toggleStatus());
        menu.add(toggleButton);

        MenuItem exitButton = new MenuItem("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        menu.add(exitButton);

        TrayIcon icon = getTrayIcon();
        icon.setImage(getImage(proxyStatus));
        icon.setImageAutoSize(true);
        icon.setPopupMenu(menu);
        icon.setToolTip(proxyStatus ? service.getProxyInfo() : "Not active");

        this.lastStatus = proxyStatus;
    }

    private Image getImage(boolean proxyStatus) {
        String imagePath = proxyStatus ? ImagePaths.ON_IMAGE_PATH : ImagePaths.OFF_IMAGE_PATH;
        InputStream imageStream = Optional.ofNullable(this.getClass().getResourceAsStream(imagePath))
                .orElseThrow(() -> new TrayException("Image not found: " + imagePath));
        try {
            byte[] imageBytes = imageStream.readAllBytes();
            return Toolkit.getDefaultToolkit().createImage(imageBytes);
        } catch (IOException ex) {
            throw new TrayException("Image not found " + imagePath);
        }
    }

    public void toggleStatus() {
        RegistryService service = new RegistryService();
        service.setValue(!service.getValue());
        updateStatus();
    }

    private TrayIcon getTrayIcon() {
        if (tray.getTrayIcons().length == 0) {
            try {
                tray.add(new TrayIcon(getImage(false)));
            } catch (AWTException ex) {
                throw new TrayException("Failed to add tray icon", ex);
            } catch (IllegalArgumentException ignored) {}
        }
        return tray.getTrayIcons()[0];
    }
}
