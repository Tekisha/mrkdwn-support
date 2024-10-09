package main_task

object Parser {
  private def tokenize(input: String): Either[String, List[String]] = {
    val pattern = """\(|\)|[a-zA-Z0-9]+|\s+""".r
    val tokens = pattern.findAllIn(input).toList.filterNot(_.trim.isEmpty)

    if (tokens.nonEmpty) Right(tokens)
    else Left("Input string is empty or contains only whitespace.")
  }

  private def parse(tokens: List[String]): Either[String, (Tree, List[String])] = tokens match {
    case Nil => Left("Unexpected end of input.")

    case "(" :: rest =>
      parseChildren(rest).map {
        case (children, remainingTokens) => (Node(children), remainingTokens)
      }

    case id :: rest if id.matches("[a-zA-Z0-9]+") => Right((Identifier(id), rest))

    case ")" :: rest => Left("Unmatched closing parenthesis.")

    case _ => Left("Invalid token encountered.")
  }

  private def parseChildren(tokens: List[String], acc: List[Tree] = List()): Either[String, (List[Tree], List[String])] = tokens match {
    case Nil => Left("Unexpected end of input inside of node.")

    case ")" :: rest => Right((acc, rest))

    case _ => parse(tokens).flatMap {
      case (tree, remainingTokens) => parseChildren(remainingTokens, acc :+ tree)
    }
  }

  def parseString(input: String): Either[String, Tree] = {
    tokenize(input) match {
      case Left(error) => Left(s"Tokenization failed: $error")

      case Right(tokens) => {
        parse(tokens) match {
          case Left(error) => Left(s"Parsing failed: $error")

          case Right((tree, Nil)) => Right(tree)

          case Right((_, remainingTokens)) => Left(s"Parsing failed: Unconsumed tokens remain - ${remainingTokens.mkString(" ")}")
        }
      }
    }
  }
}
