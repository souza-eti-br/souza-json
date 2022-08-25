package br.eti.souza.json;

import br.eti.souza.exception.SystemException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Representa uma lista criada apartir de um json.
 * @author Alan Moraes Souza
 */
public class JSONList extends ArrayList<IJSON> implements IJSON {

    /** Construtor padrão. */
    private JSONList() {
    }

    /**
     * Cria JSONList apartir do json.
     * @param json JSON que será convertido.
     * @return JSONList correspondente.
     * @throws SystemException Caso o json não seja válido.
     */
    protected static JSONList fromJSON(String json) throws SystemException {
        if (json == null) {
            throw new SystemException("invalid.json.format");
        } else {
            json = json.trim();
            if (!json.startsWith("[") || !json.endsWith("]")) {
                throw new SystemException("invalid.json.format");
            }
        }
        json = json.substring(1, json.length() - 1).trim();
        JSONList list = new JSONList();
        while (!json.isEmpty()) {
            String value = JSON.utilityGetStartAsString(json);
            if (value != null) {
                json = json.substring(value.length()).trim();
                list.add(JSONValue.fromJSON(value));
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
                list.add(JSONValue.fromJSON(value));
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
                list.add(JSONValue.fromJSON(value));
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
                list.add(JSONValue.fromJSON(value));
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
                list.add(JSONList.fromJSON(value));
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
                list.add(JSONObject.fromJSON(value));
                if (json.startsWith(",")) {
                    json = json.substring(1).trim();
                } else {
                    break;
                }
            }
        }
        if (!json.isEmpty()) {
            throw new SystemException("invalid.json.format");
        }
        return list;
    }

    /**
     * Cria JSONList apartir do object.
     * @param object Objeto que será convertido.
     * @return JSONList correspondente.
     * @throws SystemException Caso o objeto não seja válido.
     */
    protected static JSONList fromObject(Object object) throws SystemException {
        if (object == null) {
            throw new SystemException("invalid.object.null");
        } else if (object instanceof byte[]) {
            var list = (byte[]) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSONValue.fromObject(item));
            }
            return result;
        } else if (object instanceof int[]) {
            var list = (int[]) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSONValue.fromObject(item));
            }
            return result;
        } else if (object instanceof long[]) {
            var list = (long[]) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSONValue.fromObject(item));
            }
            return result;
        } else if (object instanceof float[]) {
            var list = (float[]) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSONValue.fromObject(item));
            }
            return result;
        } else if (object instanceof double[]) {
            var list = (double[]) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSONValue.fromObject(item));
            }
            return result;
        } else if (object instanceof char[]) {
            var list = (char[]) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSONValue.fromObject(item));
            }
            return result;
        } else if (object instanceof Object[]) {
            var list = (Object[]) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSON.recursiveFrom(item));
            }
            return result;
        } else if (object instanceof Collection) {
            var list = (Collection) object;
            JSONList result = new JSONList();
            for (Object item : list) {
                result.add(JSON.recursiveFrom(item));
            }
            return result;
        } else {
            throw new SystemException("invalid.object.unknow.list");
        }
    }

    /**
     * Retorna json correspondente a lista.
     * @return JSON conrespondente a lista.
     */
    @Override
    public String toJSON() {
        if (this.isEmpty()) {
            return "[]";
        } else {
            StringBuilder json = new StringBuilder("[ ").append(this.get(0).toJSON());
            for (int i = 1; i < this.size(); i++) {
                json.append(", ").append(this.get(i).toJSON());
            }
            return json.append(" ]").toString();
        }
    }
}
