package com.sistemamatricula;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {
        boolean sair = false;

        try {
            emf = Persistence.createEntityManagerFactory("sistemamatricula");
            em = emf.createEntityManager();
        } catch (PersistenceException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return;
        }

        try {
            em.getTransaction().begin();

            while (!sair) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Aluno");
                System.out.println("2. Professor");
                System.out.println("3. Sistema de Matrículas");
                System.out.println("9. Sair");

                int escolha = scanner.nextInt();
                scanner.nextLine();

                switch (escolha) {
                    case 1:
                        menuAluno(scanner);
                        break;
                    case 2:
                        menuProfessor(scanner);
                        break;
                    case 3:
                        menuSistemaMatriculas(scanner);
                        break;
                    case 9:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }

            em.getTransaction().commit();
        } catch (RollbackException e) {
            System.err.println("Erro ao tentar commitar a transação: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
            scanner.close();
    }

    public static void menuAluno(Scanner scanner) {
        boolean sair = false;
        Cobranca sistemaCobranca = new Cobranca(); 
        SistemaMatriculas sistemaMatriculas = new SistemaMatriculas();

        while (!sair) {
            System.out.println("Escolha uma opção para Aluno:");
            System.out.println("1. Matricular em Disciplina");
            System.out.println("2. Cancelar Matrícula");
            System.out.println("9. Voltar");

            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                sistemaMatriculas.abrirPeriodoMatriculas();
                    System.out.println("Digite o nome do aluno:");
                    String nomeAlunoMatricula = scanner.nextLine();
                    Aluno alunoMatricula = em.createQuery("SELECT a FROM Aluno a WHERE a.nome = :nome", Aluno.class)
                            .setParameter("nome", nomeAlunoMatricula)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);
                    System.out.println("Digite o nome da disciplina para matrícula:");
                    String nomeDisciplinaMatricula = scanner.nextLine();
                    Disciplina disciplinaMatricula = em.createQuery("SELECT d FROM Disciplina d WHERE d.nome = :nome", Disciplina.class)
                            .setParameter("nome", nomeDisciplinaMatricula)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);

                    if (alunoMatricula != null && disciplinaMatricula != null) {
                        sistemaMatriculas.matricularAluno(alunoMatricula, disciplinaMatricula);
                        sistemaCobranca.notificarCobranca(alunoMatricula, disciplinaMatricula);
                        System.out.println("Aluno matriculado com sucesso.");
                    } else {
                        System.out.println("Aluno ou disciplina não encontrados.");
                    }
                    break;

                case 2:
                    System.out.println("Digite o nome do aluno:");
                    String nomeAlunoCancelamento = scanner.nextLine();
                    Aluno alunoCancelamento = em.createQuery("SELECT a FROM Aluno a WHERE a.nome = :nome", Aluno.class)
                            .setParameter("nome", nomeAlunoCancelamento)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);

                    System.out.println("Digite o nome da disciplina para cancelamento:");
                    String nomeDisciplinaCancelamento = scanner.nextLine();
                    Disciplina disciplinaCancelamento = em.createQuery("SELECT d FROM Disciplina d WHERE d.nome = :nome", Disciplina.class)
                            .setParameter("nome", nomeDisciplinaCancelamento)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);
                    
                    if (alunoCancelamento != null && disciplinaCancelamento != null) {
                        sistemaMatriculas.cancelarMatricula(alunoCancelamento, disciplinaCancelamento);
                        System.out.println("Matrícula cancelada com sucesso.");
                    } else {
                        System.out.println("Aluno ou disciplina não encontrados.");
                    }
                    break;

                case 9:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void menuProfessor(Scanner scanner) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma opção para Professor:");
            System.out.println("1. Listar Alunos da Sala");
            System.out.println("9. Voltar");

            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome do professor:");
                    String nomeProfessor = scanner.nextLine();

                    Professor professor = em.createQuery("SELECT p FROM Professor p WHERE p.nome = :nome", Professor.class)
                            .setParameter("nome", nomeProfessor)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);

                    if (professor != null) {
                        System.out.println("Digite o nome da disciplina:");
                        String nomeDisciplina = scanner.nextLine();

                        Disciplina disciplinaEscolhida = professor.getDisciplinas().stream()
                                .filter(d -> d.getNome().equalsIgnoreCase(nomeDisciplina))
                                .findFirst()
                                .orElse(null);

                        if (disciplinaEscolhida != null) {
                            List<Aluno> alunosMatriculados = professor.listarAlunosMatriculados(disciplinaEscolhida);
                            if (alunosMatriculados != null && !alunosMatriculados.isEmpty()) {
                                System.out.println("Alunos matriculados na disciplina " + nomeDisciplina + ":");
                                for (Aluno aluno : alunosMatriculados) {
                                    System.out.println(aluno.getNome());
                                }
                            } else {
                                System.out.println("Nenhum aluno matriculado nesta disciplina.");
                            }
                        } else {
                            System.out.println("Disciplina não encontrada ou o professor não leciona essa disciplina.");
                        }
                    } else {
                        System.out.println("Professor não encontrado.");
                    }
                    break;

                case 9:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void menuSistemaMatriculas(Scanner scanner) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma opção para o Sistema de Matrículas:");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Cadastrar Disciplina");
            System.out.println("4. Cadastrar Curso");
            System.out.println("5. Gerar Currículo");
            System.out.println("9. Voltar");

            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                System.out.println("Digite o nome do aluno:");
                String nomeAluno = scanner.nextLine();
                System.out.println("Digite o número da matrícula:");
                String matricula = scanner.nextLine();
                System.out.println("Digite o nome do curso:");
                String nomeCurso = scanner.nextLine();
            
                Curso cursoExistente = em.createQuery("SELECT c FROM Curso c WHERE c.nome = :nomeCurso", Curso.class)
                        .setParameter("nomeCurso", nomeCurso)
                        .getResultStream()
                        .findFirst()
                        .orElse(null);
            
                Curso curso;
                if (cursoExistente != null) {
                    curso = cursoExistente;
                    System.out.println("Curso existente encontrado: " + curso.getNome());
                    Aluno aluno = new Aluno("senha123", nomeAluno, matricula, curso);
                    em.persist(aluno);
                    System.out.println("Aluno cadastrado com sucesso.");
                } else {
                    curso = new Curso(nomeCurso, 0); 
                    em.persist(curso); 
                    System.out.println("Novo curso cadastrado com sucesso.");
                    Aluno aluno = new Aluno("senha123", nomeAluno, matricula, curso);
                    em.persist(aluno);
                    System.out.println("Aluno cadastrado com sucesso.");
                }
                break;

                case 2:
                    System.out.println("Digite o nome do professor:");
                    String nomeProfessorCadastro = scanner.nextLine();
                    System.out.println("Digite o nome da disciplina associada:");
                    String nomeDisciplinaAssociada = scanner.nextLine();

                    Disciplina disciplinaAssociada = em.createQuery("SELECT d FROM Disciplina d WHERE d.nome = :nome", Disciplina.class)
                            .setParameter("nome", nomeDisciplinaAssociada)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);

                    Professor professorCadastro = new Professor(nomeProfessorCadastro, "senha123");
                    em.persist(professorCadastro);
                    if (disciplinaAssociada != null) {
                        professorCadastro.addDisciplinas(disciplinaAssociada);
                        System.out.println("Professor cadastrado com sucesso.");
                    } else {
                        System.out.println("Disciplina associada não encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("Digite o nome da disciplina:");
                    String nomeDisciplina = scanner.nextLine();
                    System.out.println("Digite o código da disciplina:");
                    int codigoDisciplina = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.println("Digite o nome do professor:");
                    String nomeProfessor = scanner.nextLine();

                    Professor professor = em.createQuery("SELECT p FROM Professor p WHERE p.nome = :nome", Professor.class)
                            .setParameter("nome", nomeProfessor)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);

                    if (professor == null) {
                        professor = new Professor(nomeProfessor, "senha123");
                        em.persist(professor);
                    }

                    System.out.println("Digite o nome do curso:");
                    String cursoNome = scanner.nextLine();

                    Curso cursoE = em.createQuery("SELECT c FROM Curso c WHERE c.nome = :nome", Curso.class)
                            .setParameter("nome", cursoNome)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);

                    if (cursoE == null) {
                        System.out.println("Digite o número de créditos do curso:");
                        int numCreditos = scanner.nextInt();
                        scanner.nextLine(); 
                        curso = new Curso(cursoNome, numCreditos);
                        em.persist(curso);

                        Disciplina disciplina = new Disciplina(nomeDisciplina, codigoDisciplina, professor, curso);
                        curso.adicionarDisciplina(disciplina);
                        em.persist(disciplina);
                        System.out.println("Disciplina cadastrada com sucesso.");
                    } else {
                        Disciplina disciplina = new Disciplina(nomeDisciplina, codigoDisciplina, professor, cursoE);
                        cursoE.adicionarDisciplina(disciplina);
                        em.persist(disciplina);
                        System.out.println("Disciplina cadastrada com sucesso.");
                    }
                    break;

                case 4:
                    System.out.println("Digite o nome do curso:");
                    String nomeCursoCadastro = scanner.nextLine();
                    System.out.println("Digite o número de créditos do curso:");
                    int numCreditos = scanner.nextInt();
                    scanner.nextLine();

                    Curso cursoCadastro = new Curso(nomeCursoCadastro, numCreditos);
                    em.persist(cursoCadastro);
                    System.out.println("Curso cadastrado com sucesso.");
                    break;

                case 5:
                    gerarCurriculo();
                    break;

                case 9:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void gerarCurriculo() {
        
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            List<Disciplina> disciplinas = em.createQuery("SELECT d FROM Disciplina d", Disciplina.class).getResultList();

            System.out.println("Currículo das disciplinas:");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("Disciplina: " + disciplina.getNome());
                System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
                System.out.println("Professor: " + (disciplina.getProfessor() != null ? disciplina.getProfessor().getNome() : "Sem professor"));
                System.out.println("Curso: " + disciplina.getCurso().getNome());
                System.out.println("Número de Créditos: " + disciplina.getnumCreditos());
                System.out.println("Obrigatório: " + (disciplina.isObrigatorio() ? "Sim" : "Não"));
                System.out.println("Ativa: " + (disciplina.isAtiva() ? "Sim" : "Não"));
                System.out.println("------------------------------------");
            }

            List<Curso> cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
            System.out.println("=== Cursos ===");
            for (Curso curso : cursos) {
                System.out.println("Curso: " + curso.getNome());
                System.out.println("Número de Créditos: " + curso.getNumCreditos());
                System.out.println("Disciplinas:");
                for (Disciplina disciplina : curso.getDisciplinas()) {
                    System.out.println("  - " + disciplina.getNome() + " (Créditos: " + disciplina.getnumCreditos() + ")");
                }
                System.out.println("------------------------------------");
            }

            List<Aluno> alunos = em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
            System.out.println("=== Alunos ===");
            for (Aluno aluno : alunos) {
                System.out.println("Aluno: " + aluno.getNome());
                System.out.println("------------------------------------");
            }

            List<Professor> professores = em.createQuery("SELECT p FROM Professor p", Professor.class).getResultList();
            System.out.println("=== Professores ===");
            for (Professor professor : professores) {
                System.out.println("Professor: " + professor.getNome());
                System.out.println("Disciplinas Ministradas:");
                for (Disciplina disciplina : professor.getDisciplinas()) {
                    System.out.println("  - " + disciplina.getNome() + " (Carga Horária: " + disciplina.getCargaHoraria() + ")");
                }
                System.out.println("------------------------------------");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        
    }
}