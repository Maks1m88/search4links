package ru.same.strategies;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class AwareNamingStrategy extends SpringPhysicalNamingStrategy {
    private Properties properties;

    public AwareNamingStrategy() {
        properties = new Properties();
        try {
            ClassLoader loader = AwareNamingStrategy.class.getClassLoader();
            URL resource = loader.getResource("application.properties");
            properties.load(resource.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String nameByProperties = getNameByProperties(name);
        if (nameByProperties == null) return super.toPhysicalSchemaName(name, jdbcEnvironment);
        if (nameByProperties.isEmpty()) return null;
        return getIdentifier(nameByProperties, name.isQuoted(), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String nameByProperties = getNameByProperties(name);
        if (nameByProperties == null) return super.toPhysicalTableName(name, jdbcEnvironment);
        if (nameByProperties.isEmpty()) return null;
        return getIdentifier(nameByProperties, name.isQuoted(), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String nameByProperties = getNameByProperties(name);
        if (nameByProperties == null) return super.toPhysicalColumnName(name, jdbcEnvironment);
        if (nameByProperties.isEmpty()) return null;
        return getIdentifier(nameByProperties, name.isQuoted(), jdbcEnvironment);
    }

    private String getNameByProperties(Identifier name) {
        if (name == null) {
            return null;
        }
        return properties.getProperty("load." + name.getText());
    }
}
