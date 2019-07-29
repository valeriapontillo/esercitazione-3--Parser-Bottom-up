<h1>Esercitazione-3--Parser-Bottom-up</h1>

Data la seguente grammatica:

```flex
N = {Program, Stmt, Expr, Term},
   T = {ID, IF, THEN, RELOP, NUMBER, ;, ASSIGN},
   S = P 

           P-> P;Stmt 
           P->Stmt
           Stmt->IF Expr THEN Stmt 
           Stmt->ID ASSIGN Expr 
           Expr->Term  RELOP Term
           Expr->Term 
           Term->ID 
           Term->NUMBER
```

Dove i token sono per lo più quelli definiti nell'<a href="https://github.com/valeriapontillo92/esercitazione1">esercitazione 1</a>.
Esempi di frasi corrette sono:
```flex
a := 5
if a > 1 then if x < y then z <-- x >= 3;   a <-- 5; b <-- a <= b
```
Costruire un parser a discesa ricorsiva, verificando prima se la grammatica data può essere usata per costruire parser top-down.
Nel caso non lo sia, applicare le trasformazioni necessarie senza alterare il linguaggio dato.

<h2>Passo 1: Eliminazione delle ambiguità</h2>
Genericamente una grammatica è ambigua quando è possibile ottenere più di un albero di derivazione, cioè 
esiste una frase che ammette 2 o più alberi di derivazioni.
La grammatica non risulta ambigua.

<h2>Passo 2: Eliminazione della ricorsione a sinistra</h2>
Una grammatica è detta ricorsiva a sinistra se ha un non terminale A per cui esiste una derivazione A =>+ Aa
I parser top-down non possono gestire grammatiche con ricorsione a sinistra.
La grammatica è ricorsiva a sinistra.
Dopo aver eliminato la ricorsione, la grammatica si presenta nel seguente modo:

```flex
    P->S P'
    P'->;SP'|esp
    S->IF E THEN S
    S->ID ASSIGN E
    E->T RELOP T
    E->T
    T->ID
    T->NUMBER
```
<h2>Passo 3: Fattorizzazione a sinistra</h2>
La fattorizzazione sinistra è una trasformazione che si attua quando non sono chiare due produzioni alternative per un non
terminale A. Le due produzioni possono essere riscritte in modo da differire tale scelta, finché non saranno stati letti 
abbastanza simboli d'ingresso da poter prendere una decisione corretta.
La grammatica deve essere trasformata tramite una fattorizzazione sinistra
Dopo averla fattorizzata, la grammatica risulta nel seguente modo:

```flex
  P->SP'
  P'->;SP'|eps
  S->IF E THEN S
  S->ID ASSIGN E
  E->TE'
  E'->RELOP T
  E'-> eps
  T->ID
  T->NUMBER
```
