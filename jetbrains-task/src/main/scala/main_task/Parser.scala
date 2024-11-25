package main_task

object Parser {
  /**
   *  Tokenizes the input string into meaningful components (identifiers, parentheses, and whitespace).
   *  Filters out any extraneous whitespace and returns the list of tokens.
   *  If the input is empty or contains only whitespace, it returns and error message.
   */
  private def tokenize(input: String): Either[String, List[String]] = {
    val validPattern = """_|\(|\)|[a-zA-Z0-9]+""".r
    val invalidPattern = """[^()\s\w]+""".r

    invalidPattern.findFirstIn(input) match {
      case Some(invalidToken) => Left(s"Invalid token encountered - $invalidToken")
      case None =>
        val tokens = validPattern.findAllIn(input).toList.filterNot(_.trim.isEmpty)
        if (tokens.nonEmpty) Right(tokens) else Left("Input string is empty or contains only whitespace.")
    }
  }

  /**
   * Parses a list of tokens into a Tree structure (either a Node or Identifier)
   * Differentiates between Nodes (denoted by parentheses) and Identifiers (alphanumeric strings).
   * Handles errors like unmatched parentheses, unexpected end of input, and invalid tokens.
   */
  private def parse(tokens: List[String]): Either[String, (Tree, List[String])] = tokens match {
    case Nil => Left("Parsing failed: Unexpected end of input inside of node.")

    case "(" :: rest =>
      parseChildren(rest).map {
        case (children, remainingTokens) => (Node(children), remainingTokens)
      }
      
    case id :: rest if id.matches("[a-zA-Z0-9]+") => Right((Identifier(id), rest))

    case ")" :: rest => Left("Parsing failed: Unmatched closing parenthesis.")

    case "_" :: rest =>  Right((Placeholder(), rest))
      
    case invalidToken :: _ => Left(s"Parsing failed: Invalid token encountered - $invalidToken")
  }

  /**
   *Helper function to recursively parse children trees for a Node.
   * Accumulates the parsed trees and returns them along with any remaining tokens.
   * Handles nested structures and ensures no unmatched parentheses.
   */
  private def parseChildren(tokens: List[String], acc: List[Tree] = List()): Either[String, (List[Tree], List[String])] = tokens match {
    case Nil => Left("Parsing failed: Unexpected end of input inside of node.")

    case ")" :: rest => Right((acc, rest))

    case _ => parse(tokens).flatMap {
      case (tree, remainingTokens) => parseChildren(remainingTokens, acc :+ tree)
    }
  }

  /**
   * Main function to parse a string input representing a tree in TL format.
   * First tokenizes the input, then parses it into a Tree structure.
   * Returns an error if tokenization or parsing fails, or if there are unconsumed tokens remaining.
   */
  def parseString(input: String): Either[String, Tree] = {
    tokenize(input) match {
      case Left(error) => Left(s"Tokenization failed: $error")

      case Right(tokens) => {
        parse(tokens) match {
          case Left(error) => Left(error)

          case Right((tree, Nil)) => Right(tree)

          case Right((_, remainingTokens)) if remainingTokens.forall(_ == ")") => Left("Parsing failed: Unmatched closing parenthesis.")

          case Right((_, remainingTokens)) => Left(s"Parsing failed: Unconsumed tokens remain - ${remainingTokens.mkString(" ")}")
        }
      }
    }
  }
}
