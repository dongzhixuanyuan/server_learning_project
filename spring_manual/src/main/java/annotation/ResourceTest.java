package annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author magina
 * @description 文件描述
 * @date 2020/11/15 9:49 上午
 */
@Component
@Lazy
class ResourceTest {
    @Value("classpath:/test")
    private Resource mResource;

    @PostConstruct
    public void init() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(mResource.getInputStream(), StandardCharsets.UTF_8))) {
            System.out.println(reader.lines().collect(Collectors.joining("\n")));
        }

    }
}
