package br.eti.souza.json;

/**
 * Representa uma lista criada apartir de um json.
 * @author Alan Moraes Souza
 */
public class JSONValue implements IJSON {

    /** Valor */
    private final Object value;

    /**
     * Contrutor que define o valor.
     * @param value Valor.
     */
    private JSONValue(Object value) {
        this.value = value;
    }

    /**
     * Cria JSONValue apartir do json.
     * @param json JSON que será convertido.
     * @return JSONValue correspondente.
     * @throws IllegalArgumentException Caso o json não seja válido.
     */
    protected static JSONValue fromJSON(String json) {
        if (json == null) {
            throw new IllegalArgumentException("invalid.json.format");
        } else if ("null".equals(json) || "undefined".equals(json)) {
            return new JSONValue(null);
        } else if ("true".equals(json) || "TRUE".equals(json)) {
            return new JSONValue(Boolean.TRUE);
        } else if ("false".equals(json) || "FALSE".equals(json)) {
            return new JSONValue(Boolean.FALSE);
        } else if ("0123456789-".contains(String.valueOf(json.charAt(0)))) {
            if (json.indexOf('.') >= 0) {
                return new JSONValue(Double.valueOf(json));
            } else {
                return new JSONValue(Long.valueOf(json));
            }
        } else if (json.startsWith("\"") && json.endsWith("\"")) {
            return new JSONValue(json.substring(1, json.length() - 1).replaceAll("\\\\\"", "\""));
        } else {
            throw new IllegalArgumentException("invalid.json.format");
        }
    }

    /**
     * Cria JSONValue apartir do object.
     * @param object Objeto que será convertido.
     * @return JSONValue correspondente.
     */
    protected static JSONValue fromObject(Object object) {
        return new JSONValue(object);
    }

    /**
     * Retorna json correspondente ao valor.
     * @return JSON conrespondente ao valor.
     */
    @Override
    public String toJSON() {
        if (this.value == null) {
            return "null";
        } else if (this.value instanceof Number || this.value instanceof Boolean) {
            return this.value.toString();
        } else {
            return new StringBuilder("\"").append(this.value.toString().replaceAll("\"", "\\\\\"")).append("\"").toString();
        }
    }
}
