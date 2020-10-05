package br.com.luisbsl.agibankanalisedados.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilitário para geração de diretórios e sua árvore de arquivos
 * Nomes de diretórios gerados com delimitadores específicos do Sistema Operacional  
 * 
 * @author luislima
 *
 */
public final class SistemaOperacionalUtils {

	private SistemaOperacionalUtils() {
	}

	/**
	 * Retorna árvore de diretórios com seus delimitadores
	 * 
	 * @param diretorios lista com os nomes das pastas que deseja criar a árvore 
	 * @return retorna árvore de diretório a partir da HOME do usuário
	 */
	public static final String getDiretorioArvore(List<String> diretorios) {
		return System.getProperty("user.home")
						.concat(getDiretorioArvoreDelimitador().concat(diretorios.stream().collect(Collectors.joining(getDiretorioArvoreDelimitador())))
						.concat(getDiretorioArvoreDelimitador()));
	}

	/**
	 * Verifica o Sistema Operacional para definir o delimitador correto 
	 * - Unix Like: /
	 * - Windows: \\ 
	 * 
	 * @return retorna o delimitador que separa a árvore de diretório
	 */
	public static final String getDiretorioArvoreDelimitador() {
		if (System.getProperty("os.name").contains("Windows")) {
			return "\\";
		}
		return "/";
	}
	
	/**
	 * Verifica se o diretório existe, caso não exista o mesmo será criado
	 * 
	 * @param diretorio nome do diretório
	 * @throws IOException retorna exceção caso não seja possível criar a árvore de diretório
	 */
	public static final void verificarDiretorio(String diretorio) throws IOException {
		if(Boolean.FALSE.equals(new File(diretorio).exists())) {
			java.nio.file.Files.createDirectory(Paths.get(diretorio));
		}
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
	}

}
