package br.eti.souza.json;

import jakarta.json.Json;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitária usada para converter objeto em json e vice-versa.
 * @author Alan Moraes Souza
 */
public final class JSON {

    /** Logger desta classe. */
    private static final Logger LOGGER = Logger.getLogger(JSON.class.getName());

    /**
     * Converte objeto para JSON.
     * @param object Objeto a ser convertido.
     * @return Representação JSON do objeto.
     */
    public static String toJSON(Object object) {
        if (object == null) {
            return "null";
        } else if (Character.class.isInstance(object)) {
            return Json.createValue(Character.class.cast(object).toString()).toString();
        } else if (String.class.isInstance(object)) {
            return Json.createValue(String.class.cast(object)).toString();
        } else if (Boolean.class.isInstance(object)) {
            return Boolean.class.cast(object).toString();
        } else if (Byte.class.isInstance(object)) {
            return Json.createValue(Byte.class.cast(object)).toString();
        } else if (Integer.class.isInstance(object)) {
            return Json.createValue(Integer.class.cast(object)).toString();
        } else if (Long.class.isInstance(object)) {
            return Json.createValue(Long.class.cast(object)).toString();
        } else if (Float.class.isInstance(object)) {
            return Json.createValue(Float.class.cast(object)).toString();
        } else if (Double.class.isInstance(object)) {
            return Json.createValue(Double.class.cast(object)).toString();
        } else if (BigInteger.class.isInstance(object)) {
            return Json.createValue(BigInteger.class.cast(object)).toString();
        } else if (BigDecimal.class.isInstance(object)) {
            return Json.createValue(BigDecimal.class.cast(object)).toString();
        } else if (object instanceof Map) {
            var map = Map.class.cast(object);
            var mapForJSON = new LinkedHashMap<String, Object>();
            for (var key : map.keySet()) {
                mapForJSON.put(key.toString(), map.get(key));
            }
            return Json.createObjectBuilder(mapForJSON).build().toString();
        } else if (object instanceof Collection) {
            var list = Collection.class.cast(object);
            var json = Json.createArrayBuilder(list);
            return json.build().toString();
        } else if (object.getClass().isArray()) {
            var json = new StringBuilder("[");
            if (object instanceof boolean[]) {
                var array = (boolean[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else if (object instanceof byte[]) {
                var array = (byte[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else if (object instanceof short[]) {
                var array = (short[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else if (object instanceof char[]) {
                var array = (char[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else if (object instanceof int[]) {
                var array = (int[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else if (object instanceof long[]) {
                var array = (long[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else if (object instanceof float[]) {
                var array = (float[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else if (object instanceof double[]) {
                var array = (double[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            } else {
                var array = (Object[]) object;
                for (var item : array) {
                    if (json.length() > 1) {
                        json.append(",");
                    }
                    json.append(" ").append(JSON.toJSON(item));
                }
                if (json.length() > 1) {
                    json.append(" ");
                }
                return json.append("]").toString();
            }
        } else {
            var json = new StringBuilder("{");
            for (var method : object.getClass().getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers()) && method.getParameterCount() == 0) {
                    var name = method.getName();
                    if ((name.length() > 3 && name.startsWith("get")) || (name.length() > 2 && name.startsWith("is") && (Boolean.class.isAssignableFrom(method.getReturnType()) || method.getReturnType().getName().equals("boolean")))) {
                        if (name.startsWith("get")) {
                            name = name.substring(3, 4).toLowerCase() + name.substring(4);
                        } else {
                            name = name.substring(2, 3).toLowerCase() + name.substring(3);
                        }
                        if (json.length() > 1) {
                            json.append(",");
                        }
                        json.append(" ").append(Json.createValue(name)).append(": ");
                        try {
                            json.append(JSON.toJSON(method.invoke(object)));
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            JSON.LOGGER.log(Level.WARNING, "Não foi possível invocar o método ".concat(object.getClass().getName()).concat(".").concat(method.getName()).concat(" ao gerar o JSON."), e);
                            json.append(Json.createValue("error.invoking.this.method=".concat(e.getMessage())));
                        }
                    }
                }
            }
            if (json.length() > 1) {
                json.append(" ");
            }
            return json.append("}").toString();
        }
    }
}
