package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    void run(){
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);
        long lastId = 0;
        List<Article> articleList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();
        Member loginedMember;
        while (true) {
            System.out.printf("명령어) ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                System.out.println("시스템을 종료합니다");
                break;
            } else if (command.equals("회원가입")) {
                String userId;
                String password;
                String passwordconfirm;

                while (true) {
                    System.out.printf("아이디 : ");
                    userId = sc.nextLine();
                    boolean duplicatedId = false;
                    for (int i = 0; i < memberList.size(); i++) {
                        if (memberList.get(i).userId.equals(userId)) {
                            duplicatedId = true;
                        }
                        if (duplicatedId) {
                            System.out.println("존재하는 아이디입니다");
                            continue;
                        }
                    }
                    break;
                }
                while (true) {
                    System.out.printf("비번 : ");
                    password = sc.nextLine();
                    System.out.printf("비번확인 : ");
                    passwordconfirm = sc.nextLine();

                    if (password.equals(passwordconfirm) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다");
                        continue;
                    }
                    break;
                }
                LocalDate now = LocalDate.now();

                Member member = new Member(userId, password, now.toString());
                memberList.add(member);

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

