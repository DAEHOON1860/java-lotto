package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    View view = new View();
    Domain domain = new Domain();
    Lotto lotto;
    Exception ex = new Exception();
    int inMoney;
    int bonus;
    int[] list = new int[8];
    long result;
    String input;
    String[] str;
    List<Integer> lottoList = new ArrayList<>();

    void start() {
        if (inputStart()) return;
        Computer[] computers = getComputers();
        if (inputWinningNumber()) return;
        if (inputBonus()) return;
        lotto.getNumbers().add(bonus);
        domain.checkWinning(computers, list, lotto);
        view.printResult(list);
        result = domain.checkWinningMoney(list);
        view.printBenfit(inMoney, result);
    }

    private boolean inputBonus() {
        view.inputBonus();
        input = Console.readLine();
        try {
            ex.inputWinningBonus(input);
        }catch (IllegalArgumentException e){
            return true;
        }
        bonus = Integer.parseInt(input);
        return false;
    }

    private boolean inputStart() {
        view.startMention();
        input = Console.readLine();
        try{
            ex.initialInput(input);
        }catch (IllegalArgumentException e){
            return true;
        }
        inMoney = Integer.parseInt(input);
        return false;
    }

    private Computer[] getComputers() {
        Computer[] computers = new Computer[inMoney / 1000];
        for (int i = 0; i < inMoney / 1000; i++) computers[i] = new Computer();
        view.purchaseNumber(inMoney / 1000, computers);
        System.out.println();
        return computers;
    }


    private boolean inputWinningNumber() {
        view.inputNumber();
        input = Console.readLine();
        str = input.split(",");
        for (String s : str) {
            lottoList.add(Integer.parseInt(s));
        }
        try {
            lotto = new Lotto(lottoList);
        }catch (IllegalArgumentException e){
            return true;
        }
        System.out.println();
        return false;
    }
}
