package Program;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import Connection.connectionFactory;
import DAO.registroDAO;
import Modelo.registro;

public class application {

	public static void main(String[] args) throws SQLException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		try (Scanner sc = new Scanner(System.in)) {
			try (Connection connection = new connectionFactory().recuperaConexao()) {
				registroDAO registrodao = new registroDAO(connection);

				int nov;
				do {
					System.out.println("\nEcolhar a operaçao que deseja:");
					System.out.print("\n1 LISTAR == 2 INSERIR == 3 DELETAR == 4 ALTERAR :");
					int op = sc.nextInt();
					System.out.println("");

					if (op == 1) {
						List<registro> resgistros = registrodao.listar();
						resgistros.stream().forEach(re -> System.out.println(re));

					} else if (op == 2) {

						System.out.print("Quantos registros deseja realizar: ");
						int qtd = sc.nextInt();

						for (int i = 1; i <= qtd; i++) {

							System.out.println(i + "º REGISTRO");

							System.out.print("Digite o nome que deseja inserir: ");
							String nome = sc.next();

							System.out.print("Digite a idade: ");
							int idade = sc.nextInt();

							System.out.print("Digite o RG: ");
							String rg = sc.next();

							System.out.print("Digite o CPF: ");
							String cpf = sc.next();

							System.out.print("Digite a data de nascimento (yyyy/MM/dd): ");

							java.util.Date date = new java.util.Date();
							java.sql.Date dataNasc = new java.sql.Date(date.parse(sc.next()));

							registro registros = new registro(null, nome, idade, rg, cpf, dataNasc);

							registrodao.Inserir(registros);
						}

					} else if (op == 3) {

						System.out.println("\nRegistros atuais\n");

						List<registro> resgistros = registrodao.listar();
						resgistros.stream().forEach(re -> System.out.println(re));

						System.out.print("\nDigite o ID de quem deseja DELETAR: ");
						Integer id = sc.nextInt();

						registrodao.deletar(id);

						resgistros = registrodao.listar();
						resgistros.stream().forEach(re -> System.out.println(re));
					}

					else if (op == 4) {

						List<registro> resgistros = registrodao.listar();
						resgistros.stream().forEach(re -> System.out.println(re));

						System.out.println("\nDigite o ID de que deseja ALTERAR: ");
						int id = sc.nextInt();

						System.out.print("Digite o nome que deseja substituir: ");
						String nome = sc.next();

						System.out.print("Digite a idade: ");
						int idade = sc.nextInt();

						System.out.print("Digite o RG: ");
						String rg = sc.next();

						System.out.print("Digite o CPF: ");
						String cpf = sc.next();

						System.out.print("Digite a data de nascimento (yyyy/MM/dd): ");

						java.util.Date date = new java.util.Date();
						java.sql.Date dataNasc = new java.sql.Date(date.parse(sc.next()));

						registrodao.alterar(nome, idade, rg, cpf, dataNasc, id);

						resgistros = registrodao.listar();
						resgistros.stream().forEach(re -> System.out.println(re));

					}
					System.out.println("\nOperacao realizada com SUCESSO");
					System.out.println("Deseja realizar outro OPERACAO ?");
					System.out.print("1 = SIM  0 = NAO: ");
					nov = sc.nextInt();

				} while (nov == 1);
			}
		}
	}
}