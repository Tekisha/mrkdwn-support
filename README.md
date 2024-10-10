# Warmup and TL Language Tree Manipulation in Scala

## Warmup Task: Recursive Function Implementation

### Problem
We need to implement a recursive function that calculates:
- **f(x) = f(x-1) + f(x-1)**
- **f(0) = 1**

The function doubles at each step, representing **2^x**. We implement this in three ways:
1. Basic recursive solution.
2. Memoization to optimize recursive calls.
3. A bit-shifting solution for an O(1) solution.

### Solutions
```scala
// Basic Recursive Solution
def f_recursive(x: Long): Long = {
  if (x == 0) 1
  else 2 * f_recursive(x - 1)
}

// Memoization to Reduce Redundant Computation
def f_memoization(x: Long, memo: scala.collection.mutable.Map[Long, Long] = scala.collection.mutable.Map()): Long = {
  if (x == 0) 1
  else memo.getOrElseUpdate(x, 2 * f_memoization(x - 1, memo))
}

// Optimized Bit-Shifting Solution
def f_bitshift(x: Long) = 1L << x
```

# TL Tree Task - Parsing, Equality, and Manipulation of Trees

## Task Overview

In this task, we process a simple language (TL) that describes trees using identifiers and parentheses. Trees can either be **identifiers** (alphanumeric strings) or **nodes** (nested structures with children that can be either nodes or identifiers). The main challenge involves:

1. **Representing the tree structure in Scala**.
2. **Checking equality** of trees based on identifiers or tree structure.
3. **Serializing and deserializing trees** from/to their TL representation.
4. **Replacing subtrees** within a tree based on a given search and replacement criteria.

The following grammar (in EBNF) describes the TL language:
```TREE = NODE | ID NODE = ‘(‘ TREE* ‘)’ ID = [a-zA-Z0-9]+```

### Example Tree Representations:

- `x` - Simple identifier
- `(x y)` - Node with two children (`x`, `y`)
- `((a) (b))` - Nested nodes

---

## Solution Structure

### 1. **Tree Data Structure**

The tree is represented using the following data structures in Scala:
- **`Identifier`**: Represents a leaf node with a string value.
- **`Node`**: Represents an internal node with a list of child trees.

The base trait `Tree` contains methods to:
- Check equality (`isEqual`)
- Convert the tree to a string (`toString`)

### 2. **Equality Check**

Two trees are considered equal if:
- **Identifiers**: The values of the identifiers are the same.
- **Nodes**: They have the same number of children, and all children are recursively equal.

### 3. **Serialization to TL**

The method `toString` in both `Identifier` and `Node` classes handles the conversion of trees into their TL representation:
- **Identifier**: Returns the identifier string.
- **Node**: Returns the representation in parentheses with child trees separated by spaces.

### 4. **Parsing TL Strings to Trees**

The `Parser` object contains:
- **`tokenize`**: Converts the input string into a list of tokens (identifiers, parentheses).
- **`parse`**: Parses the tokenized list into a `Tree` structure using recursive descent.
- **`parseString`**: The main entry point to convert a TL string to a `Tree`.

### 5. **Tree Replacement**

The method `replace` takes:
- The original tree.
- A `searchTree` to be found.
- A `replacement` tree to replace all occurrences of the `searchTree`.

This method recursively traverses the tree and replaces every subtree equal to `searchTree` with `replacement`.