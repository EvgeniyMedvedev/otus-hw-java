package ru.otus.bank.repository;

import ru.otus.bank.controllers.models.BaseResponse;
import ru.otus.bank.models.Banknotes;

import java.util.EnumMap;
import java.util.Map;

/**
 * Repository.
 *
 * @author Evgeniy_Medvedev
 */
public class Repository implements BankOfSwitzerland {
    private static Repository repository;

    private Map<Banknotes, Integer> vault;
    private static final int CAPACITY = 100;

    private Repository(EnumMap<Banknotes, Integer> vault) {
        this.vault = vault;
    }

    public static Repository createRepository(EnumMap<Banknotes, Integer> vault) {
        if (repository != null) {
            return repository;
        }
        return repository = new Repository(vault);
    }

    @Override
    public BaseResponse get(int amount) {
        if (amount % 10 != 0) {
            return new BaseResponse().setErrorMsg("Такая сумма недоступна для выдачи, введите кратную 10");
        }
        int money = 0;
        while (amount >= 10) {
            Banknotes banknote = howMuch(amount);
            int cursor = 0;
            Banknotes[] banknotes = Banknotes.values();

            if (vault.get(banknote) == 0) {
                for (int i = 0; i < banknotes.length; i++) {
                    if (banknotes[i] == banknote) {
                        cursor = i;
                        break;
                    }
                }
                try {
                    banknote = banknotes[cursor - 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new BaseResponse().setErrorMsg("Десяток нет, иди работай");
                }
            }

            amount = amount - banknote.getValue();
            money += banknote.getValue();
            if (vault.get(banknote) != 0) {
                vault.merge(banknote, 1, (Integer i, Integer b) -> (i - b));
            } else {
                return new BaseResponse().setErrorMsg("Я пуст внутри");
            }

        }
        return new BaseResponse().setValue(money);
    }

    @Override
    public String give(Map<Banknotes, Integer> request) {
        String res = null;
        for (Map.Entry<Banknotes, Integer> entry : request.entrySet()){
            if (vault.get(entry.getKey()) + entry.getValue() <= CAPACITY) {
                vault.merge(entry.getKey(), entry.getValue(), Integer::sum);
                res = "Готово";
            } else {
                return "Я полон " + entry.getKey().getValue() + "-ми";
            }
        }
        return res;
    }

    private Banknotes howMuch(int amount) {
        if (amount < 10) {
            throw new IllegalArgumentException("Номинала меньше 10 не существует, выберете сумму больше");
        } else if (amount < 20) {
            return Banknotes.TEN;
        } else if (amount < 50) {
            return Banknotes.TWENTY;
        } else if (amount < 100) {
            return Banknotes.FIFTY;
        } else if (amount < 200) {
            return Banknotes.ONE_HUNDRED;
        } else if (amount < 500) {
            return Banknotes.TWO_HUNDRED;
        } else if (amount < 1000) {
            return Banknotes.FIVE_HUNDRED;
        } else if (amount < 2000) {
            return Banknotes.ONE_THOUSAND;
        } else if (amount < 5000) {
            return Banknotes.TWO_THOUSAND;
        } else {
            return Banknotes.FIVE_THOUSAND;
        }
    }


    public void clearContext() {
        vault = new EnumMap<>(Banknotes.class);
        for (Banknotes banknote : Banknotes.values()) {
            vault.put(banknote, 50);
        }
    }
}