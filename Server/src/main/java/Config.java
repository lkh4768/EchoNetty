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
    private String serverIp;
    private int serverPort;
    private final Logger logger = LogManager.getLogger(Config.class);

    Config() {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<XMLConfiguration> builder =
                new FileBasedConfigurationBuilder<XMLConfiguration>(XMLConfiguration.class)
                        .configure(params.xml().setFileName("config.xml"));
        try {
            XMLConfiguration config = builder.getConfiguration();
            serverPort = config.getInt("server[@port]", 6666);

            logger.debug(toString());
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getServerIp() {
        return serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String toString() {
        return ("Server / Port(" + serverPort + ")");
    }
}
