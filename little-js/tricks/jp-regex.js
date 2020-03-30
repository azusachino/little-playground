/**
 * The ranges are (as quoted from the site):
 3000 - 303f: Japanese-style punctuation
 3040 - 309f: Hiragana
 30a0 - 30ff: Katakana
 ff00 - ff9f: Full-width Roman characters and half-width Katakana
 4e00 - 9faf: CJK unified ideographs - Common and uncommon Kanji
 3400 - 4dbf: CJK unified ideographs Extension A - Rare Kanji
 * */

const pattern = /[\u3000-\u303f\u3040-\u309f\u30a0-\u30ff\uff00-\uff9f\u4e00-\u9faf\u3400-\u4dbf]/

const checkJp = (str) => {
    if (str !== null && str.length > 0) {
        return pattern.test(str)
    }
}
