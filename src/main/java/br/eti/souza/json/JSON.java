package br.eti.souza.json;

import java.util.Collection;

/**
 * Usado na conversão de objeto ou listas em json, e vice versa.
 * @author Alan Moraes Souza
 */
public final class JSON {

    /**
     * Converter objeto ou lista em json.
     * @param <T> Classe do objeto.
     * @param object Objeto ou lista que será convertido em json.
     * @return JSON correspondete ao objeto ou lista.
     * @throws IllegalArgumentException Caso o objeto ou lista não possa ser convertido em json.
     */
    public static <T> String fromObject(T object) {
        if (object == null) {
            throw new IllegalArgumentException("illegal.object.null");
        }
        if (String.class.isInstance(object)) {
            throw new IllegalArgumentException("illegal.object.string");
        }
        if (Boolean.class.isInstance(object)) {
            throw new IllegalArgumentException("illegal.object.boolean");
        }
        if (Number.class.isInstance(object)) {
            throw new IllegalArgumentException("illegal.object.number");
        }
        if (Character.class.isInstance(object)) {
            throw new IllegalArgumentException("illegal.object.character");
        }
        return JSON.recursiveFrom(object).toJSON();
    }

    /**
     * Converter objeto ou lista em json (recursivo).
     * @param object Objeto ou lista que será convertido em json.
     * @return IJSON correspondete ao objeto ou lista.
     * @throws IllegalArgumentException Caso o objeto ou lista não possa ser convertido em json.
     */
    protected static IJSON recursiveFrom(Object object) {
        if ((object == null) || String.class.isInstance(object) || Boolean.class.isInstance(object) || Number.class.isInstance(object) || Character.class.isInstance(object)) {
            return JSONValue.fromObject(object);
        } else if ((Collection.class.isInstance(object)) || (object instanceof Object[]) || (object instanceof byte[]) || (object instanceof int[]) || (object instanceof long[]) || (object instanceof float[]) || (object instanceof double[]) || (object instanceof char[])) {
            return JSONList.fromObject(object);
        } else {
            return JSONObject.fromObject(object);
        }
    }

    /**
     * Converter json em JSONObject ou JSONList.
     * @param json JSON que será convertido em objeto ou lista
     * @return Objeto ou lista correspondente ao json.
     * @throws IllegalArgumentException Caso o json não possa ser convertido em objeto ou lista.
     */
    public static IJSON toObject(String json) {
        if (json != null) {
            json = json.trim();
            if (json.startsWith("{") && json.endsWith("}")) {
                return JSONObject.fromJSON(json);
            } else if (json.startsWith("[") && json.endsWith("]")) {
                return JSONList.fromJSON(json);
            }
        }
        throw new IllegalArgumentException("invalid.json.format");
    }

    /**
     * Obter trecho equivalente a string do json.
     * @param json JSON que avaliado.
     * @return String encontrada ou null se json não inicia com string.
     */
    protected static String utilityGetStartAsString(String json) {
        if (json != null && json.startsWith("\"")) {
            boolean lastIsEscape = false;
            for (int i = 1; i < json.length(); i++) {
                char last = json.charAt(i);
                if ((last == '"') && !lastIsEscape) {
                    return json.substring(0, i + 1);
                } else {
                    lastIsEscape = (last == '\\');
                }
            }
        }
        return null;
    }

    /**
     * Obter trecho equivalente a boolean do json.
     * @param json JSON que avaliado.
     * @return String encontrada ou null se json não inicia com boolean.
     */
    protected static String utilityGetStartAsBoolean(String json) {
        if (json != null) {
            if (json.startsWith("true") || json.startsWith("TRUE")) {
                return json.substring(0, 4);
            } else if (json.startsWith("false") || json.startsWith("FALSE")) {
                return json.substring(0, 5);
            }
        }
        return null;
    }

    /**
     * Obter trecho equivalente a número do json.
     * @param json JSON que avaliado.
     * @return String encontrada ou null se json não inicia com número.
     */
    protected static String utilityGetStartAsNumber(String json) {
        if (json != null) {
            if ("0123456789-".contains(String.valueOf(json.charAt(0)))) {
                for (int i = 1; i < json.length(); i++) {
                    char last = json.charAt(i);
                    if (!"0123456789-.".contains(String.valueOf(last))) {
                        return json.substring(0, i);
                    }
                }
                return json;
            }
        }
        return null;
    }

    /**
     * Obter trecho equivalente a null do json.
     * @param json JSON que avaliado.
     * @return String encontrada ou null se json não inicia com null ou undefined.
     */
    protected static String utilityGetStartAsNull(String json) {
        if (json != null) {
            if (json.startsWith("null") || json.startsWith("NULL")) {
                return json.substring(0, 4);
            } else if (json.startsWith("undefined") || json.startsWith("UNDEFINED")) {
                return json.substring(0, 9);
            }
        }
        return null;
    }

    /**
     * Obter trecho equivalente a lista do json.
     * @param json JSON que avaliado.
     * @return String encontrada ou null se json não inicia com uma lista.
     */
    protected static String utilityGetStartAsList(String json) {
        if ((json != null) && json.startsWith("[")) {
            boolean inString = false;
            boolean lastIsEscape = false;
            int countOpenChar = 1;
            for (int i = 1; i < json.length(); i++) {
                char last = json.charAt(i);
                if ((last == '"') && !lastIsEscape) {
                    inString = !inString;
                } else {
                    lastIsEscape = (last == '\\');
                }
                if (!inString) {
                    if ('[' == last) {
                        countOpenChar++;
                    } else if (']' == last) {
                        if (--countOpenChar == 0) {
                            return json.substring(0, i + 1);
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Obter trecho equivalente a objeto do json.
     * @param json JSON que avaliado.
     * @return String encontrada ou null se json não inicia com um objeto.
     */
    protected static String utilityGetStartAsObjeto(String json) {
        if ((json != null) && json.startsWith("{")) {
            boolean inString = false;
            boolean lastIsEscape = false;
            int countOpenChar = 1;
            for (int i = 1; i < json.length(); i++) {
                char last = json.charAt(i);
                if ((last == '"') && !lastIsEscape) {
                    inString = !inString;
                } else {
                    lastIsEscape = (last == '\\');
                }
                if (!inString) {
                    if ('{' == last) {
                        countOpenChar++;
                    } else if ('}' == last) {
                        if (--countOpenChar == 0) {
                            return json.substring(0, i + 1);
                        }
                    }
                }
            }
        }
        return null;
    }
}
