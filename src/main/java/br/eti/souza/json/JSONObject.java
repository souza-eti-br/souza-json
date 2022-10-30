package br.eti.souza.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Representa um objeto criado apartir de um json.
 * @author Alan Moraes Souza
 */
public class JSONObject extends LinkedHashMap<String, IJSON> implements IJSON {

    /** Construtor padrão. */
    private JSONObject() {
    }

    /**
     * Cria JSONObject apartir do json.
     * @param json JSON que será convertido.
     * @return JSONObject correspondente.
     * @throws IllegalArgumentException Caso o json não seja válido.
     */
    protected static JSONObject fromJSON(String json) {
        if (json == null) {
            throw new IllegalArgumentException("invalid.json.format");
        } else {
            json = json.trim();
            if (!json.startsWith("{") || !json.endsWith("}")) {
                throw new IllegalArgumentException("invalid.json.format");
            }
        }
        json = json.substring(1, json.length() - 1).trim();
        JSONObject object = new JSONObject();
        while (!json.isEmpty()) {
            String key = JSON.utilityGetStartAsString(json);
            if (key == null) {
                throw new IllegalArgumentException("invalid.json.format");
            } else {
                json = json.substring(key.length()).trim();
                key = key.substring(1, key.length() - 1).replaceAll("\\\"", "\"");
                if (!json.startsWith(":")) {
                    throw new IllegalArgumentException("invalid.json.format");
                } else {
                    json = json.substring(1).trim();
                    String value = JSON.utilityGetStartAsString(json);
                    if (value != null) {
                        json = json.substring(value.length()).trim();
                        object.put(key, JSONValue.fromJSON(value));
                        if (json.startsWith(",")) {
                            json = json.substring(1).trim();
                            continue;
                        } else {
                            break;
                        }
                    }
                    value = JSON.utilityGetStartAsBoolean(json);
                    if (value != null) {
                        json = json.substring(value.length()).trim();
                        object.put(key, JSONValue.fromJSON(value));
                        if (json.startsWith(",")) {
                            json = json.substring(1).trim();
                            continue;
                        } else {
                            break;
                        }
                    }
                    value = JSON.utilityGetStartAsNumber(json);
                    if (value != null) {
                        json = json.substring(value.length()).trim();
                        object.put(key, JSONValue.fromJSON(value));
                        if (json.startsWith(",")) {
                            json = json.substring(1).trim();
                            continue;
                        } else {
                            break;
                        }
                    }
                    value = JSON.utilityGetStartAsNull(json);
                    if (value != null) {
                        json = json.substring(value.length()).trim();
                        object.put(key, JSONValue.fromJSON(value));
                        if (json.startsWith(",")) {
                            json = json.substring(1).trim();
                            continue;
                        } else {
                            break;
                        }
                    }
                    value = JSON.utilityGetStartAsList(json);
                    if (value != null) {
                        json = json.substring(value.length()).trim();
                        object.put(key, JSONList.fromJSON(value));
                        if (json.startsWith(",")) {
                            json = json.substring(1).trim();
                            continue;
                        } else {
                            break;
                        }
                    }
                    value = JSON.utilityGetStartAsObjeto(json);
                    if (value != null) {
                        json = json.substring(value.length()).trim();
                        object.put(key, JSONObject.fromJSON(value));
                        if (json.startsWith(",")) {
                            json = json.substring(1).trim();
                            continue;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (!json.isEmpty()) {
            throw new IllegalArgumentException("invalid.json.format");
        }
        return object;
    }

    /**
     * Cria JSONObject apartir do object.
     * @param object Objeto que será convertido.
     * @return JSONObject correspondente.
     * @throws IllegalArgumentException Caso o objeto não seja válido.
     */
    protected static JSONObject fromObject(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("invalid.object.null");
        } else if (object instanceof String) {
            throw new IllegalArgumentException("invalid.object.string");
        } else if (object instanceof Character) {
            throw new IllegalArgumentException("invalid.object.character");
        } else if (object instanceof Number) {
            throw new IllegalArgumentException("invalid.object.number");
        } else if (object instanceof Boolean) {
            throw new IllegalArgumentException("invalid.object.boolean");
        } else if (Map.class.isInstance(object)) {
            JSONObject result = new JSONObject();
            var map = Map.class.cast(object);
            var keys = map.keySet();
            for (Object key : keys) {
                result.put(String.valueOf(object), JSON.recursiveFrom(map.get(key)));
            }
            return result;
        } else {
            JSONObject result = new JSONObject();
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                Object value = result.getObjectFieldValue(object, field);
                if (value != null) {
                    result.put(field.getName(), JSON.recursiveFrom(value));
                }
            }
            return result;
        }
    }

    /**
     * Retorna valor de um atributo do objeto, se existir methodo get ou is, com valor não nulo.
     * @param object Objeto do qual será obtido o valor do atributo.
     * @param field Atributo desejado.
     * @return Valor de um atributo do objeto, se existir methodo get ou is, com valor não nulo.
     */
    private Object getObjectFieldValue(Object object, Field field) {
        String fieldName = field.getName();
        String partNameMethod = String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
        try {
            Method methodGet = object.getClass().getMethod("get" + partNameMethod);
            return methodGet.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            try {
                Method methodIs = object.getClass().getMethod("is" + partNameMethod);
                return methodIs.invoke(object);
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                return null;
            }
        }
    }

    /**
     * Retorna json correspondente ao objeto.
     * @return JSON conrespondente ao objeto.
     */
    @Override
    public String toJSON() {
        if (this.isEmpty()) {
            return "{}";
        } else {
            ArrayList<String> keys = new ArrayList<>(this.keySet());
            StringBuilder json = new StringBuilder("{ \"").append(keys.get(0).replaceAll("\"", "\\\"")).append("\": ").append(this.get(keys.get(0)).toJSON());
            for (int i = 1; i < this.size(); i++) {
                json.append(", \"").append(keys.get(i).replaceAll("\"", "\\\"")).append("\": ").append(this.get(keys.get(i)).toJSON());
            }
            return json.append(" }").toString();
        }
    }
}
