package coronado.yamir.transformador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/a-codigo-morse/{texto}")
    public String transformarACodigoMorse(@PathVariable String texto) {
        String textoTransformado = "";

        char[] abecedario = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // Array del código Morse correspondiente
        String[] codigoMorse = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-",
                ".--", "-..-", "-.--", "--.."
        };

        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            textoTransformado = convertirLetraACodigoMorse(abecedario, letra, textoTransformado, codigoMorse);

        }
        return textoTransformado;
    }

    private static String convertirLetraACodigoMorse(char[] abecedario, char letra, String textoTransformado, String[] codigoMorse) {
        for (int j = 0; j < abecedario.length; j++) {
            String letraABC = String.valueOf(abecedario[j]);
            if (letraABC.equalsIgnoreCase(String.valueOf(letra))) {
                textoTransformado += codigoMorse[j];
            }
        }
        return textoTransformado;
    }

    @GetMapping("/a-texto/{codigoMorse}")
    public String transformarDeCodigoMorse(@PathVariable String codigoMorse) {
        String textoTransformado = "";

        char[] abecedario = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // Array del código Morse correspondiente
        String[] codigoMorseArray = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-",
                ".--", "-..-", "-.--", "--.."
        };

        String[] morseWords = codigoMorse.split(" / ");
        for (String morseWord : morseWords) {
            String[] morseLetters = morseWord.split(" ");
            for (String morseLetter : morseLetters) {
                textoTransformado += convertirCodigoMorseALetra(abecedario, morseLetter, codigoMorseArray);
            }
            textoTransformado += " ";
        }

        return textoTransformado.trim();
    }

    private static char convertirCodigoMorseALetra(char[] abecedario, String morseLetter, String[] codigoMorseArray) {
        for (int j = 0; j < codigoMorseArray.length; j++) {
            if (codigoMorseArray[j].equals(morseLetter)) {
                return abecedario[j];
            }
        }
        return ' ';
    }
}
