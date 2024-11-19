package upt.proj.condominio.client.main;


import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import upt.proj.condominio.client.config.AppConfig;
import upt.proj.condominio.client.service.ContaClientService;
import upt.proj.condominio.client.service.PredioClientService;
import upt.proj.condominio.model.Conta;
import upt.proj.condominio.model.DonoPredio;
import upt.proj.condominio.model.Empresa;
import upt.proj.condominio.model.Predio;
import upt.proj.condominio.model.User;
import verificacoes.Checks;

public class ClientMain {

	public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Scanner sc = new Scanner(System.in);
		
		CreateConta();
        
        sc.close();
        context.close();

	}

	public static void CreateConta() {
		Scanner sc = new Scanner(System.in);
		String resp = "";
        boolean ccriada = false;
        System.out.println("Deseja criar conta como: \n1- Dono do Predio\n2- Morador\n3- Empresa \n4- Voltar atras");
        do {
            resp = sc.nextLine();
            if (Checks.SeInteger(resp)) {
                switch (Integer.parseInt(resp)) {
                    case 1 : // DONO DO PREDIO
						String username = "";
						do {
							System.out.println("Qual e o seu username?");
							username = sc.nextLine();
						} while (username.equals("") || (username.indexOf(" ") == 0));

						if (readConta(username) != null) {
							System.out.println("Nome de utilizador ja existente.");
							break;
						} else {
							
							String email = "";
							Boolean emailvalido = false;
							do {
								System.out.println("Digite o seu email:");
								email = sc.nextLine();
								if ((email.indexOf("@") != -1) && (email.substring(email.indexOf("@")+1).indexOf(".") >= 1)) {
									emailvalido = true;
								} else System.out.println("Email invalido.");
								if (emailvalido) {
									if (readEmail(email) != null) {
										System.out.println("Email ja existente.");
										emailvalido = false;
									}
								}
							} while(email.equals("") || emailvalido == false);
							System.out.println("Qual e a sua password?");
							String password = "";
							do {
								password = sc.nextLine();
								if (password.equals("")|| password.equals(" ")) {
									System.out.println("Por favor insira uma password.");
								}
							} while (password.equals("") || password.equals(" "));
							System.out.println("Qual e a sua idade?");
							String idadeStr;
							Integer idade = null;
							do {
								idadeStr = sc.nextLine();
								if (Checks.SeInteger(idadeStr)) {
									idade = Integer.parseInt(idadeStr);
									if (idade < 18) {
										System.out.println("Por favor insira uma idade maior que 18.");
										idade = null;
									}
								} else {
									System.out.println("Por favor insira um numero inteiro.");
								}
							}while(!Checks.SeInteger(idadeStr) || idade == null);

							createDonoP(username, email, password, idade);
							ccriada = true;
						}
					break;
                    case 2 : // MORADOR
						System.out.println("Qual e o seu username?");
						username = "";
						do {
							username = sc.nextLine();
						} while (username.equals("") ||  (username.indexOf(" ") == 0));
						if (readConta(username) != null) {
							System.out.println("Nome de utilizador ja existente.");
							break;
						} else {
							String email = "";
							Boolean emailvalido = false;
							do {
								System.out.println("Digite o seu email:");
								email = sc.nextLine();
								if ((email.indexOf("@") != -1) && (email.substring(email.indexOf("@")+1).indexOf(".") >= 1)) {
									emailvalido = true;
								} else System.out.println("Email invalido.");
								if (emailvalido) {
									if (readEmail(email) != null) {
										System.out.println("Email ja existente.");
										emailvalido = false;
									}
								}
							} while(email.equals("") || emailvalido == false);
							System.out.println("Qual e a sua password?");
							String password = "";
							do {
								password = sc.nextLine();
								if (password.equals("")|| password.equals(" ")) {
									System.out.println("Por favor insira uma password.");
								}
							} while (password.equals("") || password.equals(" "));
							System.out.println("Qual e a sua idade?");
							String idadeStr;
							Integer idade = null;
							do {
								idadeStr = sc.nextLine();
								if (Checks.SeInteger(idadeStr)) {
									idade = Integer.parseInt(idadeStr);
									if (idade < 18) {
										System.out.println("Por favor insira uma idade maior que 18.");
										idade = null;
									}
								} else {
									System.out.println("Por favor insira um numero inteiro.");
								}
							}while(!Checks.SeInteger(idadeStr) || idade == null);
	
							createUser(username, email, password, idade);
							ccriada = true;
						}
						
                    break;
                    case 3 : // EMPRESA
					System.out.println("Qual e o seu username?");
					username = "";
					do {
						username = sc.nextLine();
					} while (username.equals("") ||  (username.indexOf(" ") == 0));
					if (readConta(username) != null) {
						System.out.println("Nome de utilizador ja existente.");
						break;
					} else {
						String email = "";
						Boolean emailvalido = false;
						do {
							System.out.println("Digite o seu email:");
							email = sc.nextLine();
							if ((email.indexOf("@") != -1) && (email.substring(email.indexOf("@")+1).indexOf(".") >= 1)) {
								emailvalido = true;
							} else System.out.println("Email invalido.");
							if (emailvalido) {
								if (readEmail(email) != null) {
									System.out.println("Email ja existente.");
									emailvalido = false;
								}
							}
						} while(email.equals("") || emailvalido == false);
						System.out.println("Qual e a sua password?");
						String password = "";
						do {
							password = sc.nextLine();
							if (password.equals("")|| password.equals(" ")) {
								System.out.println("Por favor insira uma password.");
							}
						} while (password.equals("") || password.equals(" "));
						System.out.println("Que tipos de servicos oferece?");
						String tipo = sc.nextLine();
						System.out.println("Em que zona atua?");
						String zona = sc.nextLine();
						System.out.println("Qual e o preco dos seus servicos?");
						Float preco = null;
						String precoStr;
						do {
							precoStr = sc.nextLine();
							precoStr = precoStr.replace(",", ".");
							precoStr = precoStr + "f";
							if (Checks.SeFloat(precoStr)) {
								preco = Float.parseFloat(precoStr);
							} else {
								System.out.println("Por favor insira um numero.");
							}
						}while(!Checks.SeFloat(precoStr) || preco == null);
	
						createEmpresa(username, email, password, tipo, zona, preco);
						ccriada = true;
					}
                    break;
                    case 4 : System.out.println("Voltando atras..."); break;
                    default : System.out.println("Opcao invalida... \n1- Dono do Predio\n2- Morador\n3- Empresa\n4- Voltar atras"); break;
                }
            } else {
                System.out.println("Por favor insira um numero inteiro de 1 a 4.");
            }
        } while (!resp.equals("4") && ccriada == false);
		


	}

	public static Conta readConta(String username) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		Conta conta = contaClientService.getContaByUsername(username);
		context.close();
		return conta;
	}

	public static Conta readEmail(String email) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		Conta conta = contaClientService.getContaByEmail(email);
		context.close();
		return conta;
	}

	public static void createUser(String username,String email,String password,Integer idade) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		contaClientService.createUser(username, email, password, idade);
		context.close();
	}

	public static void createDonoP(String username,String email,String password,Integer idade) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		contaClientService.createDonoP(username, email, password, idade);
		context.close();
	}

	public static void createEmpresa(String username,String email,String password,String tiposervice,String zona,Float preco) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		contaClientService.createEmpresa(username, email, password, tiposervice, zona, preco);
		context.close();
	}




}
