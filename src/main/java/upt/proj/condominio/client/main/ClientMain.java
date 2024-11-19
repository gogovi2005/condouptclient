package upt.proj.condominio.client.main;


import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import upt.proj.condominio.client.config.AppConfig;
import upt.proj.condominio.client.service.ApartamentoClientService;
import upt.proj.condominio.client.service.ContaClientService;
import upt.proj.condominio.client.service.GastosClientService;
import upt.proj.condominio.client.service.PredioClientService;
import upt.proj.condominio.model.Apartamento;
import upt.proj.condominio.model.Conta;
import upt.proj.condominio.model.Gastos;
import upt.proj.condominio.model.Predio;
import verificacoes.Checks;

public class ClientMain {

	public static void main(String[] args) { //FEITO
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Scanner sc = new Scanner(System.in);
        String resp = "";
        System.out.println("Bem vindo ao sistema de condominio");
        System.out.println("Por favor faça login com o seu username ou email e password. Se nao tiver conta, por favor crie uma. \n 1- Login \n 2- Registar-se \n 3- Sair");
        do {
            resp = sc.nextLine();
            switch (resp) {
                case "1" : System.out.println("Login"); login(); break;
                case "2" : System.out.println("Criar conta.."); CreateConta(); break;
                case "3" : System.out.println("A sair..."); break;
                default : System.out.println("Opcao invalida... \n 1- Login \n 2- Registar-se \n 3- Sair"); break;
            }
        } while (!resp.equals("3"));
        context.close();
		sc.close();
	}


	public static void login() { //FEITO
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual e o seu username ou email?");
		String usermail = sc.nextLine();
		boolean email = false;
		if (((usermail.indexOf("@") != -1) && (usermail.substring(usermail.indexOf("@")+1).indexOf(".") >= 1))) {
			email = true;
		}
		Conta user = null;
		if (email) {
			user = readEmail(usermail);
		} else {
			user = readConta(usermail);
		}
		if (user != null) {
			System.out.println("Qual e a sua password?");
			String password = null;
			do {
				password = sc.nextLine();
				if (user.getPassword().equals(password)) {
					System.out.println("Login efetuado com sucesso.");
					UI(user);
				} else {
					System.out.println("Password incorreta.");
				}
			} while (!password.equals(user.getPassword()));
		} else {
			System.out.println("Utilizador nao encontrado.");
			
		}
	}


	public static void CreateConta() { //FEITO
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

	public static Conta readConta(String username) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		Conta conta = contaClientService.getContaByUsername(username);
		context.close();
		return conta;
	}

	public static Conta readEmail(String email) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		Conta conta = contaClientService.getContaByEmail(email);
		context.close();
		return conta;
	}

	public static void createUser(String username,String email,String password,Integer idade) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		contaClientService.createUser(username, email, password, idade);
		context.close();
	}

	public static void createDonoP(String username,String email,String password,Integer idade) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		contaClientService.createDonoP(username, email, password, idade);
		context.close();
	}

	public static void createEmpresa(String username,String email,String password,String tiposervice,String zona,Float preco) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContaClientService contaClientService = context.getBean(ContaClientService.class);
		contaClientService.createEmpresa(username, email, password, tiposervice, zona, preco);
		context.close();
	}

	public static void UI(Conta user){ //FEITO
		
		System.out.println("Bem-vindo " + user.getUsername() + "!");
		String tipo = user.getClass().getName().substring(user.getClass().getName().lastIndexOf(".")+1);
		if (tipo.equals("DonoPredio")) {
			UI_DonoPredio(user);
		} else if (tipo.equals("User")) {
			UI_User(user);
		} else if (tipo.equals("Empresa")) {
			UI_Empresa(user);
		}
	
	}

	public static void UI_DonoPredio(Conta user) { //feito?
		Scanner sc = new Scanner(System.in);
		System.out.println("1- Registar predio\n2- Ver os seus predios\n3- Ver apartamentos no seu predio\n4- Sair");
		String resp;
		do {
			resp = sc.nextLine();
				switch (resp) {
					case "1" : registarPredio(user); break;
					case "2" : lerPredios(user); break;
					case "3" : lerApartamentos(user); break;
					case "4" : System.out.println("A sair...");  break;
					default : System.out.println("Opcao invalida...\n1- Registar predio\n2- Ver os seus predios\n3- Ver apartamentos no seu predio\n4- Sair"); break;
				}
		} while (!resp.equals("4"));
		
	}

	public static void UI_User(Conta user) { //FEITO
		Scanner sc = new Scanner(System.in);
		System.out.println("1- Registar gastos\n2- Ver gastos\n3- Registar Apartamento \n4- Sair");
		String resp;
		do {
			resp = sc.nextLine();
				switch (resp) {
					case "1" : RegistarGastos(user); break;
					case "2" : LerGastos(user); break;
					case "3" : criarApartamento(user);  break;
					case "4" : System.out.println("A sair..."); break;
					default : System.out.println("Opcao invalida...\n1- Registar gastos\n2- Ver gastos\n3- Registar Apartamento \n4- Sair"); break;
				}
		} while (!resp.equals("4"));
	}

	public static void UI_Empresa(Conta user) { //FEITO
		System.out.println("INDEV");
	}

	public static void RegistarGastos(Conta user) { //FEITO
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual o ano do registo");
		String anoStr;
		Integer ano = null;
		do {
			anoStr = sc.nextLine();
			if (Checks.SeInteger(anoStr)) {
				ano = Integer.parseInt(anoStr);
			} else {
				System.out.println("Por favor insira um numero inteiro.");
			}
		}while(!Checks.SeInteger(anoStr) || ano == null);
		System.out.println("Qual o mes do registo");
		String mesStr;
		Integer mes = null;
		do {
			mesStr = sc.nextLine();
			if (Checks.SeInteger(mesStr)) {
				mes = Integer.parseInt(mesStr);
				if (mes < 1 || mes > 12) {
					System.out.println("Por favor insira um mes entre 1 e 12.");
					mes = null;
				}
			} else {
				System.out.println("Por favor insira um numero inteiro.");
			}
		}while(!Checks.SeInteger(mesStr) || mes == null);
		System.out.println("Quanto gasta de agua por mes?");
		String aguaStr = null;
		Float agua = null;
		do {
			aguaStr = sc.nextLine();
			aguaStr = aguaStr.replace(",", ".");
			aguaStr = aguaStr + "f";
			if (Checks.SeFloat(aguaStr)) {
				agua = Float.parseFloat(aguaStr);
			} else {
				System.out.println("Por favor insira um numero.");
			}
		}while(!Checks.SeDouble(aguaStr) || agua == null);
		System.out.println("Quanto gasta de gas por mes?");
		String gasStr = null;
		Float gas = null;
		do {
			gasStr = sc.nextLine();
			gasStr = gasStr.replace(",", ".");
			gasStr = gasStr + "f";
			if (Checks.SeFloat(gasStr)) {
				gas = Float.parseFloat(gasStr);
			} else {
				System.out.println("Por favor insira um numero.");
			}
		}while(!Checks.SeDouble(gasStr) || gas == null);
		System.out.println("Quanto gasta de eletricidade por mes?");
		String eletricidadeStr = null;
		Float eletricidade = null;
		do {
			eletricidadeStr = sc.nextLine();
			eletricidadeStr = eletricidadeStr.replace(",", ".");
			eletricidadeStr = eletricidadeStr + "f";
			if (Checks.SeFloat(eletricidadeStr)) {
				eletricidade = Float.parseFloat(eletricidadeStr);
			} else {
				System.out.println("Por favor insira um numero.");
			}
		}while(!Checks.SeDouble(eletricidadeStr) || eletricidade == null);
		createGasto(user, ano, mes, agua, gas, eletricidade);
		
	}

	public static void LerGastos (Conta user) { //FEITO
		List<Gastos> gastos = user.getGastos();
		if (gastos.isEmpty()) {
			System.out.println("Nao ha gastos registados.");
		} else {
			for (Gastos gasto : gastos) {
				System.out.println("<--------------------->");
				System.out.println(gasto.toString());
			}
		}
	}

	public static void createGasto(Conta user, Integer ano, Integer mes, Float agua, Float gas, Float eletricidade) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		GastosClientService gastosClientService = context.getBean(GastosClientService.class);
		gastosClientService.createGastos(user.getId(), mes, ano, agua, gas, eletricidade);
		context.close();
	}

	public static void criarApartamento(Conta user) { //FEITO
		Scanner sc = new Scanner(System.in);
			System.out.println("Qual e o nome do predio onde o apartamento se encontra?");
			String nomePredio = sc.nextLine();
			
			Predio predio = readNomePredio(nomePredio);
	
			if (predio == null) {
				System.out.println("Predio nao encontrado.");
				
			} else {
				System.out.println("Digite o tamanho do apartamento: ");
				String 	tamanhoStr;
				Integer tamanho = null;
				do {
					tamanhoStr = sc.nextLine();
					if (Checks.SeInteger(tamanhoStr)) {
						tamanho = Integer.parseInt(tamanhoStr);
					}	else {
						System.out.println("Por favor insira um número inteiro.");
					}
				}while(!Checks.SeInteger(tamanhoStr) || tamanho == null);
				
			   System.out.println("Tem direito a lugar na garagem? (S/N) ");
			   String resposta = sc.nextLine();
			   while (!resposta.equals("S") && !resposta.equals("N")) {
				   System.out.println("Digite S ou N");
				   resposta = sc.nextLine();
				   } 
			   Boolean garagem = true;
			   if(resposta == "N") {
				   garagem = false;
			   }
			   System.out.println("Digite o nº de residentes do apartamento: ");
				String 	nResidentesStr;
				Integer nResidentes = null;
				do {
					nResidentesStr = sc.nextLine();
					if (Checks.SeInteger(nResidentesStr)) {
						nResidentes = Integer.parseInt(nResidentesStr);
					}	else {
						System.out.println("Por favor insira um número inteiro.");
					}
				}while(!Checks.SeInteger(nResidentesStr) || nResidentes == null);  
				
			   System.out.println("Digite o nº de WCs do apartamento: ");
				String 	wcStr;
				Integer wc = null;
				do {
					wcStr = sc.nextLine();
					if (Checks.SeInteger(wcStr)) {
						wc = Integer.parseInt(wcStr);
					}	else {
						System.out.println("Por favor insira um número inteiro.");
					}
				}while(!Checks.SeInteger(wcStr) || wc == null);
			   System.out.println("Digite o nº do andar do apartamento: ");
				String 	andarStr;
				Integer andar = null;
				do {
					andarStr = sc.nextLine();
					if (Checks.SeInteger(andarStr)) {
						andar = Integer.parseInt(andarStr);
					}	else {
						System.out.println("Por favor insira um número inteiro.");
					}
				}while(!Checks.SeInteger(andarStr) || andar == null);
			   System.out.println("Digite a fracao do apartamento: ");
				String 	fracaoStr;
				Character fracao = null;
				do {
					fracaoStr = sc.nextLine();
					if (Checks.SeChar(fracaoStr)) {
						fracao = fracaoStr.charAt(0);
						if (Character.isDigit(fracao)) {
							System.out.println("Por favor insira um caracter letra.");
							fracao = null;
						}
					}	else {
						System.out.println("Por favor insira um caracter letra.");
					}
				}while(!Checks.SeChar(fracaoStr) || fracao == null);       
			   System.out.println("Digite o nº do apartamento: ");
				String 	nApartamentoStr;
				Integer nApartamento = null;
				do {
					nApartamentoStr = sc.nextLine();
					if (Checks.SeInteger(nApartamentoStr)) {
						nApartamento = Integer.parseInt(nApartamentoStr);
					}	else {
						System.out.println("Por favor insira um número inteiro.");
					}
				}while(!Checks.SeInteger(nApartamentoStr) || nApartamento == null);
   
			   createApartamento(user, predio,tamanho, garagem, nResidentes, wc, andar, fracao, nApartamento);
			}
	}

	public static Predio readNomePredio(String nomeP) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		PredioClientService predioClientService = context.getBean(PredioClientService.class);
		Predio predio = predioClientService.getPredioByNome(nomeP);
		context.close();
		return predio;
	}

	public static void createApartamento(Conta user, Predio predio, Integer tamanho, Boolean garagem, Integer nResidentes, Integer wc, Integer andar, Character fracao, Integer nApartamento) { //FEITO
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ApartamentoClientService apartamentoClientService = context.getBean(ApartamentoClientService.class);
		apartamentoClientService.createApartamento(user.getUsername(), predio.getId(), tamanho, garagem, nResidentes, wc, andar, fracao, nApartamento);
		context.close();

	}

	public static void lerApartamentos(Conta user) {
		for (Predio predio : user.getPredios()) {
			System.out.println("<----------------------------->");
			System.out.println("Apartamentos de " + predio.getNomeP() + ":");
			if (predio.getApartamentos().isEmpty()) {
				System.out.println("Não existem apartamentos no Predio "+predio.getNomeP()+".");
			} else {
				for (Apartamento apartamento : predio.getApartamentos()) {
					System.out.println("<--------------->");
					System.out.println(apartamento.toString());
				}
			}
		}
	}

	public static void lerPredios(Conta user) {
		System.out.println("Predios de " + user.getUsername() + ":");
		for (Predio predio : user.getPredios()) {
			System.out.println("<--------------->");
			System.out.println(predio.toString());
		}
	}

	public static void registarPredio(Conta user) {
		Scanner sc = new Scanner(System.in);
		String nomeP;
		do {
		   System.out.println("Digite o nome do prédio: ");
		   nomeP = sc.nextLine();
		    if (readNomePredio(nomeP) != null) {
			    System.out.println("Nome de prédio já existente.");
				nomeP = "";
		    }
		} while (nomeP.equals("") || (nomeP.indexOf(" ") == 0));

		String zona;
		do {
		   System.out.println("Digite a zona do prédio: ");
		   zona = sc.nextLine();
		} while (zona.equals("") || (zona.indexOf(" ") == 0));
	   
		System.out.println("Permite animais? (S/N) ");
		   String resposta = sc.nextLine();
		   while (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")) {
			   System.out.println("Digite N ou S");
			   resposta = sc.nextLine();
			   }
		   boolean animaisP = true;
		   if(resposta.equalsIgnoreCase("N")) {
			   animaisP = false;
		   }
		   
		   System.out.println("Digite o nº de andares do prédio: ");
			String 	andaresNumStr;
			Integer andaresNum = null;
			do {
				andaresNumStr = sc.nextLine();
				if (Checks.SeInteger(andaresNumStr)) {
					andaresNum = Integer.parseInt(andaresNumStr);
				}	else {
					System.out.println("Por favor insira um número inteiro.");
				}
			}while(!Checks.SeInteger(andaresNumStr) || andaresNumStr == null);
			System.out.println("Digite o nº de apartamentos por andar: ");
			String 	andaresporPisoStr;
			Integer andarespPisoNum = null;
			do {
				andaresporPisoStr = sc.nextLine();
				if (Checks.SeInteger(andaresporPisoStr)) {
					andarespPisoNum = Integer.parseInt(andaresporPisoStr);
				}	else {
					System.out.println("Por favor insira um número inteiro.");
				}
			}while(!Checks.SeInteger(andaresporPisoStr) || andaresporPisoStr == null);
		    Integer ntotalapart = andaresNum * andarespPisoNum;

			createPredio(user,nomeP,zona,ntotalapart, animaisP);
			
	}

	public static void createPredio(Conta user,String nomeP, String zona, Integer ntotalapart, Boolean animaisP) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		PredioClientService predioClientService = context.getBean(PredioClientService.class);
		predioClientService.createPredio(user.getId(),nomeP, zona, ntotalapart, animaisP);
		context.close();
	}
}
