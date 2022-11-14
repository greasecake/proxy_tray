package registry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ProcessHandler {
    public static BufferedReader getInputStreamReader(Process process) throws RegistryException {
        handleReaderErrors(new BufferedReader(new InputStreamReader(process.getErrorStream())));
        return new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    private static void handleReaderErrors(BufferedReader reader) throws RegistryException {
        String error = reader.lines().collect(Collectors.joining());
        if (!error.isEmpty()) {
            throw new RegistryException(error);
        }
    }
}
