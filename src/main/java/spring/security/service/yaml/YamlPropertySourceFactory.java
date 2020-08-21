package spring.security.service.yaml;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

public class YamlPropertySourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {

        if (resource == null) {
            return super.createPropertySource(name, resource);
        }

        String filename = resource.getResource().getFilename();
        Resource resourceEncoded = resource.getResource();

        List<PropertySource<?>> propertySourceList =
                new YamlPropertySourceLoader()
                        .load(filename, resourceEncoded);

        if (!propertySourceList.isEmpty()) {
            return propertySourceList.iterator().next();
        }
        return super.createPropertySource(name, resource);
    }

}

