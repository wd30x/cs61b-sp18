public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }


    public boolean isPalindrome(String word) {
        LinkedListDeque<Character> d = (LinkedListDeque<Character>) wordToDeque(word);
        return help(d);
    }

    private boolean help(LinkedListDeque<Character> d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        if (d.removeFirst() != d.removeLast()) {
            return false;
        }
        return help(d);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        LinkedListDeque<Character> d = (LinkedListDeque<Character>) wordToDeque(word);
        return help(d, cc);
    }

    private boolean help(LinkedListDeque<Character> d, CharacterComparator cc) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
            return false;
        }
        return help(d, cc);
    }
}
