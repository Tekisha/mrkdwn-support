import main_task._;
import org.scalatest.funsuite.AnyFunSuite

class ParserTests extends AnyFunSuite {

    test("Valid input with single identifier should parse successfully") {
        val input = "identifier"
        val result = Parser.parseString(input)

        assert(result == Right(Identifier("identifier")))
    }

    test("Valid input with simple node should parse successfully") {
        val input = "(identifier1 identifier2)"
        val result = Parser.parseString(input)

        val expectedTree = Node(List(Identifier("identifier1"), Identifier("identifier2")))
        assert(result == Right(expectedTree))
    }

    test("Valid input with nested nodes should parse successfully") {
        val input = "(identifier1 (identifier2 identifier3))"
        val result = Parser.parseString(input)

        val expectedTree = Node(List(Identifier("identifier1"), Node(List(Identifier("identifier2"), Identifier("identifier3")))))
        assert(result == Right(expectedTree))
    }

    test("Empty input should return an error") {
        val input = ""
        val result = Parser.parseString(input)

        assert(result == Left("Tokenization failed: Input string is empty or contains only whitespace."))
    }

    test("Input with unmatched opening parenthesis should return an error") {
        val input = "(identifier1 identifier2"
        val result = Parser.parseString(input)

        assert(result == Left("Parsing failed: Unexpected end of input inside of node."))
    }

    test("Input with unmatched closing parenthesis should return an error") {
        val input = "identifier1)"
        val result = Parser.parseString(input)

        assert(result == Left("Parsing failed: Unmatched closing parenthesis."))
    }

    test("Input with invalid token should return an error") {
        val input = "(identifier1 @invalidtoken)"
        val result = Parser.parseString(input)

        assert(result == Left("Tokenization failed: Invalid token encountered - @"))
    }

    test("Input with extra tokens should return an error about unconsumed tokens") {
        val input = "(identifier1 identifier2) extra"
        val result = Parser.parseString(input)

        assert(result == Left("Parsing failed: Unconsumed tokens remain - extra"))
    }

    test("Valid input with multiple nested nodes should parse correctly") {
        val input = "(a (b (c)))"
        val result = Parser.parseString(input)

        val expectedTree = Node(List(Identifier("a"), Node(List(Identifier("b"), Node(List(Identifier("c")))))))
        assert(result == Right(expectedTree))
    }

    test("Valid input with whitespace should parse successfully") {
        val input = "(  identifier1   identifier2  )"
        val result = Parser.parseString(input)

        val expectedTree = Node(List(Identifier("identifier1"), Identifier("identifier2")))
        assert(result == Right(expectedTree))
    }
}
