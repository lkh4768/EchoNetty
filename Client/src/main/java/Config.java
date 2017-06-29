import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by WES on 2017-02-18.
 */
public enum Config {
    INSTANCE;
    private int serverPort;
    private String serverIp;
    private final Logger logger = LogManager.getLogger(Config.class);

    Config() {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<XMLConfiguration> builder =
                new FileBasedConfigurationBuilder<XMLConfiguration>(XMLConfiguration.class)
                        .configure(params.xml().setFileName("config.xml"));
        try {
            XMLConfiguration config = builder.getConfiguration();
            serverIp = config.getString("server[@ip]", "127.0.0.1");
            serverPort = config.getInt("server[@port]", 6666);

            logger.debug(toString());
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String toString() {
        return (" Server / Ip(" + serverIp + "), Port(" + serverPort + ")");
    }
}
