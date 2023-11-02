package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    void run(){
        System.out.println("== 프로그램 시작 ==");
        //스캐너 초기화
        Scanner sc = new Scanner(System.in);
        // 게시글 등록을 위한 변수
        long lastId = 0;
        // 리스트는 잘 모르겠어요
        List<Article> articleList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();
        // 로그인 관리를 위한 변수
        Member loginedMember;
        while (true) {
            // 명령어 출력 매체
            System.out.printf("명령어) ");
            // 빌드에 문자열 출력을 위한 변수
            String command = sc.nextLine().trim();
            // 종료를 위한 if 조건문
            if (command.equals("종료")) {
                System.out.println("시스템을 종료합니다");
                break;
            } else if (command.equals("회원가입")) {
                // 회원가입을 원활히 할 수 있게 하는 문자열 변수
                String userId;
                String password;
                String passwordconfirm;

                while (true) {
                    // 회원가입 아이디 출력 매체
                    System.out.printf("아이디 : ");
                    // 아이디를 만들기 위한 스캐너
                    userId = sc.nextLine();
                    // 아이디 중복을 방지하기위한 변수
                    boolean duplicatedId = false;
                    // 새로운 아이디가 생성 될 때 아이디가 등록될 수 있도록 하는 조건반복문
                    for (int i = 0; i < memberList.size(); i++) {
                        if (memberList.get(i).userId.equals(userId)) {
                            duplicatedId = true;
                        }
                        // 아이디 중복을 차단하는 조건문
                        if (duplicatedId) {
                            System.out.println("존재하는 아이디입니다");
                            continue;
                        }
                    }
                    break;
                }
                while (true) {
                    //비밀번호생성을 위한 출력매체
                    System.out.printf("비번 : ");
                    // 비밀번호를 생성하기위한 스캐너
                    password = sc.nextLine();
                    //생성된 비밀번호를 확인하는 출력매체
                    System.out.printf("비번확인 : ");
                    // 비밀번호 확인을 하기위한 스캐너
                    passwordconfirm = sc.nextLine();

                    // 비밀번호를 확인했는데 일치하지 않을 때 회원가입 완료를 방지하는 조건문
                    if (password.equals(passwordconfirm) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다");
                        continue;
                    }
                    break;
                }
                // 날짜를 보여주기위한 변수
                LocalDate now = LocalDate.now();

                // 회원정보를 최적화해서 담아두기위한 객체
                Member member = new Member(userId, password, now.toString());
                // 회원정보 추가를 위한 변수
                memberList.add(member);
                // 회원가입 완료를 알리는 출력매체
                System.out.println(userId + "님 회원가입이 완료되었습니다");
            } else if (command.equals("등록")) {
                lastId++;
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String content = sc.nextLine();
                Article article = new Article(lastId , title , content);
                articleList.add(article);
                System.out.println(lastId + "번 게시글이 등록되었습니다");
            } else if (command.equals("목록")) {

                if (articleList.size() == 0) {
                    System.out.println("게시물이 없습니다.");

                } else {
                    System.out.println("번호 / 작가 / 명언\n");
                    for (int i = 0; i < articleList.size(); i++) {
                        Article article = articleList.get(i);
                        System.out.printf("%d / %s / %s\n", article.id, article.title, article.content);
                    }
                }

            } else if (command.equals("삭제")) {
                System.out.printf("삭제번호 : ");
                long id = Long.parseLong(sc.nextLine());
                long foundIndex = -1;
                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    if (article.getId() == id) {
                        foundIndex = id;
                        articleList.remove(article);
                        break;
                    }
                }
                if (foundIndex == -1) {
                    System.out.println(id + "번째 게시긓이 존재하지 않습니다");
                } else {
                    System.out.println(id + "번째 게시글이 삭제되었습니다");
                }
            } else if (command.equals("수정")) {
                System.out.printf("수정번호 : ");
                long id = Long.parseLong(sc.nextLine());
                long foundIndex = -1;
                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    if (article.getId() == id) {
                        foundIndex = id;

                        System.out.printf("기존제목 %s\n", article.getTitle());
                        String title = sc.nextLine();
                        article.Settitle(title);

                        System.out.printf("기존내용 %s\n", article.getContent());
                        String content = sc.nextLine();
                        article.Setcontent(content);

                        break;

                    }
                }
                if (foundIndex == -1) {
                    System.out.println(id + "번째 게시물은 존재하지 않습니다");
                } else {
                    System.out.println(id + "번째 게시글이 수정되었습니다");
                }

            }

        }
        sc.close();
        System.out.println("프로그램 종료");
    }
}

