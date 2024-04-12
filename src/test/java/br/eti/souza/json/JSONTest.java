package br.eti.souza.json;

import br.eti.souza.exception.I18nMessage;
import br.eti.souza.exception.SystemException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testes para br.eti.souza.json.JSON
 * @author Alan Moraes Souza
 */
public class JSONTest {

    /** Teste conversão de null em json. */
    @Test
    public void nullToJSON() {
        Assertions.assertEquals("null", JSON.toJSON(null));
    }

    /** Teste conversão de String vazia em json. */
    @Test
    public void stringEmptyToJSON() {
        Assertions.assertEquals("\"   \"", JSON.toJSON("   "));
    }

    /** Teste conversão de String em json. */
    @Test
    public void stringToJSON() {
        Assertions.assertEquals("\"teste\"", JSON.toJSON("teste"));
    }

    /** Teste conversão de Character em json. */
    @Test
    public void characterToJSON() {
        Assertions.assertEquals("\"C\"", JSON.toJSON('C'));
    }

    /** Teste conversão de Boolean em json. */
    @Test
    public void booleanToJSON() {
        Assertions.assertEquals("true", JSON.toJSON(true));
    }

    /** Teste conversão de Integer em json. */
    @Test
    public void integerToJSON() {
        Assertions.assertEquals("-2147483648", JSON.toJSON(Integer.MIN_VALUE));
        Assertions.assertEquals("2147483647", JSON.toJSON(Integer.MAX_VALUE));
    }

    /** Teste conversão de Long em json. */
    @Test
    public void longToJSON() {
        Assertions.assertEquals("-9223372036854775808", JSON.toJSON(Long.MIN_VALUE));
        Assertions.assertEquals("9223372036854775807", JSON.toJSON(Long.MAX_VALUE));
    }

    /** Teste conversão de Float em json. */
    @Test
    public void floatToJSON() {
        Assertions.assertEquals("1.4E-45", JSON.toJSON(Float.MIN_VALUE));
        Assertions.assertEquals("3.4028235E+38", JSON.toJSON(Float.MAX_VALUE));
    }

    /** Teste conversão de Double em json. */
    @Test
    public void doubleToJSON() {
        Assertions.assertEquals("4.9E-324", JSON.toJSON(Double.MIN_VALUE));
        Assertions.assertEquals("1.7976931348623157E+308", JSON.toJSON(Double.MAX_VALUE));
    }

    /** Teste conversão de List de String em json. */
    @Test
    public void listEmptyToJSON() {
        Assertions.assertEquals("[]", JSON.toJSON(new ArrayList<>()));
    }

    /** Teste conversão de List de String em json. */
    @Test
    public void listStringToJSON() {
        Assertions.assertEquals("[ \"   \", \"teste\" ]", JSON.toJSON(Arrays.asList("   ", "teste")));
    }

    /** Teste conversão de List de Character em json. */
    @Test
    public void listCharacterToJSON() {
        Assertions.assertEquals("[ \"C\", \"D\" ]", JSON.toJSON(Arrays.asList('C', 'D')));
    }

    /** Teste conversão de List de Boolean em json. */
    @Test
    public void listBooleanToJSON() {
        Assertions.assertEquals("[ true, false ]", JSON.toJSON(Arrays.asList(true, false)));
    }

    /** Teste conversão de List de Integer em json. */
    @Test
    public void listIntegerToJSON() {
        Assertions.assertEquals("[ -2147483648, 2147483647 ]", JSON.toJSON(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE)));
    }

    /** Teste conversão de List de Long em json. */
    @Test
    public void listLongToJSON() {
        Assertions.assertEquals("[ -9223372036854775808, 9223372036854775807 ]", JSON.toJSON(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE)));
    }

    /** Teste conversão de List de Float em json. */
    @Test
    public void listFloatToJSON() {
        Assertions.assertEquals("[ 1.4E-45, 3.4028235E+38 ]", JSON.toJSON(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE)));
    }

    /** Teste conversão List de de Double em json. */
    @Test
    public void listDoubleToJSON() {
        Assertions.assertEquals("[ 4.9E-324, 1.7976931348623157E+308 ]", JSON.toJSON(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE)));
    }

    /** Teste conversão de Array de String em json. */
    @Test
    public void arrayEmptyToJSON() {
        Assertions.assertEquals("[]", JSON.toJSON(new ArrayList<>().toArray()));
    }

    /** Teste conversão de Array de String em json. */
    @Test
    public void arrayStringToJSON() {
        Assertions.assertEquals("[ \"   \", \"teste\" ]", JSON.toJSON(Arrays.asList("   ", "teste").toArray()));
    }

    /** Teste conversão de Array de Character em json. */
    @Test
    public void arrayCharacterToJSON() {
        Assertions.assertEquals("[ \"C\", \"D\" ]", JSON.toJSON(Arrays.asList('C', 'D').toArray()));
    }

    /** Teste conversão de Array de Boolean em json. */
    @Test
    public void arrayBooleanToJSON() {
        Assertions.assertEquals("[ true, false ]", JSON.toJSON(Arrays.asList(true, false).toArray()));
    }

    /** Teste conversão de Array de Integer em json. */
    @Test
    public void arrayIntegerToJSON() {
        Assertions.assertEquals("[ -2147483648, 2147483647 ]", JSON.toJSON(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE).toArray()));
    }

    /** Teste conversão de Array de Long em json. */
    @Test
    public void arrayLongToJSON() {
        Assertions.assertEquals("[ -9223372036854775808, 9223372036854775807 ]", JSON.toJSON(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE).toArray()));
    }

    /** Teste conversão de Array de Float em json. */
    @Test
    public void arrayFloatToJSON() {
        Assertions.assertEquals("[ 1.4E-45, 3.4028235E+38 ]", JSON.toJSON(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE).toArray()));
    }

    /** Teste conversão de Array de Double em json. */
    @Test
    public void arrayDoubleToJSON() {
        Assertions.assertEquals("[ 4.9E-324, 1.7976931348623157E+308 ]", JSON.toJSON(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE).toArray()));
    }

    /** Teste conversão de Objeto vazio em json. */
    @Test
    public void objectEmptyToJSON() {
        Assertions.assertEquals("{}", JSON.toJSON(new Object()));
    }

    /** Teste conversão de List de Objeto vazio em json. */
    @Test
    public void listObjectEmptyToJSON() {
        Assertions.assertEquals("[ {}, {} ]", JSON.toJSON(Arrays.asList(new Object(), new Object())));
    }

    /** Teste conversão de Array de Objeto vazio em json. */
    @Test
    public void arrayObjectEmptyToJSON() {
        Assertions.assertEquals("[ {}, {} ]", JSON.toJSON(Arrays.asList(new Object(), new Object()).toArray()));
    }

    /** Teste conversão de Objeto em json. */
    @Test
    public void objectToJSON() {
        Assertions.assertEquals("{ \"i18nMessage\": { \"key\": \"key\", \"args\": [ \"arg01\", \"arg02\" ] } }", JSON.toJSON(new SystemException(new I18nMessage("key", "arg01", "arg02"), new IllegalArgumentException("mensagem"))));
        Assertions.assertEquals("{ \"text\": \"texto\", \"character\": \"C\", \"flag\": true, \"numberInt\": 2147483647, \"numberLong\": 9223372036854775807, \"numberFloat\": 3.4028235E+38, \"numberDouble\": 1.7976931348623157E+308 }", JSON.toJSON(new ObjectTest()));

    }

    /** Teste conversão de List de Objeto em json. */
    @Test
    public void listObjectToJSON() {
        Assertions.assertEquals("[ { \"i18nMessage\": { \"key\": \"key\", \"args\": [ \"arg01\", \"arg02\" ] } }, { \"text\": \"texto\", \"character\": \"C\", \"flag\": true, \"numberInt\": 2147483647, \"numberLong\": 9223372036854775807, \"numberFloat\": 3.4028235E+38, \"numberDouble\": 1.7976931348623157E+308 } ]", JSON.toJSON(Arrays.asList(new SystemException(new I18nMessage("key", "arg01", "arg02"), new IllegalArgumentException("mensagem")), new ObjectTest())));
    }

    /** Teste conversão de Array de Objeto em json. */
    @Test
    public void arrayObjectToJSON() {
        Assertions.assertEquals("[ { \"i18nMessage\": { \"key\": \"key\", \"args\": [ \"arg01\", \"arg02\" ] } }, { \"text\": \"texto\", \"character\": \"C\", \"flag\": true, \"numberInt\": 2147483647, \"numberLong\": 9223372036854775807, \"numberFloat\": 3.4028235E+38, \"numberDouble\": 1.7976931348623157E+308 } ]", JSON.toJSON(Arrays.asList(new SystemException(new I18nMessage("key", "arg01", "arg02"), new IllegalArgumentException("mensagem")), new ObjectTest()).toArray()));
    }
}

class ObjectTest {

    public ObjectTest() {
    }
    private Character character = 'C';
    private String text = "texto";
    private Boolean flag = true;
    private Integer numberInt = Integer.MAX_VALUE;
    private Long numberLong = Long.MAX_VALUE;
    private Float numberFloat = Float.MAX_VALUE;
    private Double numberDouble = Double.MAX_VALUE;

    public Character getCharacter() {
        return this.character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFlag() {
        return this.flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getNumberInt() {
        return this.numberInt;
    }

    public void setNumberInt(Integer numberInt) {
        this.numberInt = numberInt;
    }

    public Long getNumberLong() {
        return this.numberLong;
    }

    public void setNumberLong(Long numberLong) {
        this.numberLong = numberLong;
    }

    public Float getNumberFloat() {
        return this.numberFloat;
    }

    public void setNumberFloat(Float numberFloat) {
        this.numberFloat = numberFloat;
    }

    public Double getNumberDouble() {
        return this.numberDouble;
    }

    public void setNumberDouble(Double numberDouble) {
        this.numberDouble = numberDouble;
    }
}
