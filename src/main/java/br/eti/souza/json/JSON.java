package br.eti.souza.json;

import jakarta.json.Json;
import jakarta.json.JsonValue;
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
            return JsonValue.NULL.toString();
        } else if (object instanceof Character value) {
            return Json.createValue(value.toString()).toString();
        } else if (object instanceof String value) {
            return Json.createValue(value).toString();
        } else if (object instanceof Boolean value) {
            return value.toString();
        } else if (object instanceof Byte value) {
            return Json.createValue(value).toString();
        } else if (object instanceof Integer value) {
            return Json.createValue(value).toString();
        } else if (object instanceof Long value) {
            return Json.createValue(value).toString();
        } else if (object instanceof Float value) {
            return Json.createValue(value).toString();
        } else if (object instanceof Double value) {
            return Json.createValue(value).toString();
        } else if (object instanceof BigInteger value) {
            return Json.createValue(value).toString();
        } else if (object instanceof BigDecimal value) {
            return Json.createValue(value).toString();
        } else if (object instanceof Map map) {
            var mapForJSON = new LinkedHashMap<String, Object>();
            for (var key : map.keySet()) {
                mapForJSON.put(key.toString(), map.get(key));
            }
            return Json.createObjectBuilder(mapForJSON).build().toString();
        } else if (object instanceof Collection list) {
            var json = new StringBuilder("[");
            for (var item : list) {
                if (json.length() > 1) {
                    json.append(",");
                }
                json.append(" ").append(JSON.toJSON(item));
            }
            if (json.length() > 1) {
                json.append(" ");
            }
            return json.append("]").toString();
        } else if (object.getClass().isArray()) {
            var json = new StringBuilder("[");
            if (object instanceof boolean[] array) {
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
            } else if (object instanceof byte[] array) {
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
            } else if (object instanceof short[] array) {
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
            } else if (object instanceof char[] array) {
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
            } else if (object instanceof int[] array) {
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
            } else if (object instanceof long[] array) {
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
            } else if (object instanceof float[] array) {
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
            } else if (object instanceof double[] array) {
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
                    if (!"getClass".equals(name) && (name.length() > 3 && name.startsWith("get")) || (name.length() > 2 && name.startsWith("is") && (Boolean.class.isAssignableFrom(method.getReturnType()) || method.getReturnType().getName().equals("boolean")))) {
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
