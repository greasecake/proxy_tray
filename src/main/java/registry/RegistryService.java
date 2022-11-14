package registry;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static registry.RegistyConst.*;

public class RegistryService {
    public boolean getValue() throws RegistryException {
        return statusFromResponse(executeCommands(new String[]{
                    "reg", "query", keyName, "/v", statusValueName}));
    }

    public void setValue(boolean valueData) throws RegistryException {
        executeCommands(new String[]{
                    "reg", "add", keyName, "/v", statusValueName, "/d", valueData ? "1" : "0", "/t", "REG_DWORD", "/f"});
        if (valueData != getValue()) {
            throw new RegistryException(String.format("Failed to switch proxy status to %b", valueData));
        }
    }

    public String getProxyInfo() throws RegistryException {
        return proxyInfoFromResponse(executeCommands(new String[]{
                "reg", "query", keyName, "/v", proxyInfoValueName}));
    }

    private boolean statusFromResponse(BufferedReader reader) throws RegistryException {
        List<String> tokens = getTokensFromResponse(reader);
        int value = Integer.decode(tokens.get(tokens.size() - 1));
        switch (value) {
            case 1:
                return true;
            case 0:
                return false;
        }
        throw new RegistryException("Unexpected result: " + tokens);
    }

    private String proxyInfoFromResponse(BufferedReader reader) throws RegistryException {
        List<String> tokens = getTokensFromResponse(reader);
        String proxyInfo = tokens.get(tokens.size() - 1);
        if (proxyInfo.matches("[\\d.:]+")) {
            return proxyInfo;
        }
        throw new RegistryException(String.format("Unexpected answer: %s", tokens));
    }

    private List<String> getTokensFromResponse(BufferedReader reader) {
        return reader.lines()
                .filter(Predicate.not(String::isBlank))
                .flatMap(l -> Arrays.stream(l.split("\\s{4}")))
                .collect(Collectors.toList());
    }

    private BufferedReader executeCommands(String[] commands) {
        BufferedReader bufferedReader;
        try {
            Process process = Runtime.getRuntime().exec(commands);
            bufferedReader = ProcessHandler.getInputStreamReader(process);
        } catch (IOException ex) {
            throw new RegistryException(ex);
        }
        return bufferedReader;
    }
}
