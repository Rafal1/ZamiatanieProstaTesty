package algorithm;

/**
 * @author Rafal Zawadzki
 */
public class BasicSweepLineComments extends IntersectionDetection {
   // Pamiętaj stan zamiatania prostą i kolejkę zdarzeń
   LineState lineState = new LineState();
   EventQueue eq = new EventQueue();

   // Oblicz przecięcia wszystkich odcinków z tablicy odcinków
   public Hashtable<IPoint, ILineSegment[]>
                     intersections (ILineSegment[] segs) {
   // Buduj kolejkę zdarzeń z odcinków. Łącząc całą odkrywaną
   // informację, zadbaj, aby punkty się nie powtarzały
   for (ILineSegment ils : segs) {
      EventPoint ep = new EventPoint(ils.getStart());
      EventPoint old = eq.event(ep);
      if (old == null) { eq.insert(ep); } else { ep = old; }

      // Dodaj górne odcinki to ep (obiekt w kolejce)
      ep.addUpperLineSegment(ils);

      ep = new EventPoint(ils.getEnd());
      old = eq.event(ep);
      if (old == null) { eq.insert(ep); } else { ep = old; }

      // Dodaj dolne odcinki to ep (obiekt w kolejce)
      ep.addLowerLineSegment(ils);
   }

   // Zamiataj od góry do dołu, przetwarzając każdy punkt zdarzenia w kolejce
   while (!eq.isEmpty()) {
      EventPoint p = eq.min();
      handleEventPoint(p);
   }

   // Zwróć raport o wszystkich obliczonych przecięciach
   return report;

   // Przetwarzaj zdarzenia, uaktualniając stan prostej i raportując przecięcia
   private void handleEventPoint (EventPoint ep) {
      // Znajdź w stanie prostej odcinki – jeśli istnieją – na lewo
      // (i na prawo) od ep. Przecięcia mogą wystąpić tylko między
      // odcinkami sąsiadującymi. Zacznij od najbliższych, ponieważ
      // w trakcie zamiatania prostą w dół znajdziemy każde następne
      // przecięcie, którym się (na razie) nie zajmujemy.
      AugmentedNode<ILineSegment> left = lineState.leftNeighbor(ep);
      AugmentedNode<ILineSegment> right = lineState.rightNeighbor(ep);

      // Określ przecięcia (ints) sąsiadujących odcinków i weź odcinki
      // górne (ups) i dolne (lows) w tym punkcie zdarzeń. Przecięcie
      // istnieje, jeśli z każdym punktem zdarzeń jest skojarzony
      // więcej niż 1 odcinek
      lineState.determineIntersecting(ep, left, right);
      List<ILineSegment> ints = ep.intersectingSegments();
      List<ILineSegment> ups = ep.upperEndpointSegments();
      List<ILineSegment> lows = ep.lowerEndpointSegments();
      if (lows.size() + ups.size() + ints.size() > 1) {
         record (ep.p, new List[]{lows,ups,ints});
      }

      // Usuń wszystko z lewej, aż następnikiem lewego okaże się prawy.
      // Potem uaktualnij punkt zamiatania, dzięki czemu przecięcia będą
      // uporządkowane. Wstawiane są tylko odcinki górne i przecięcia,
      // ponieważ pozostają one nadal aktywne.
      lineState.deleteRange(left, right);
      lineState.setSweepPoint(ep.p);
      boolean update = false;
      if (!ups.isEmpty()) {
         lineState.insertSegments(ups);
         update = true;
      }
      if (!ints.isEmpty()) {
         lineState.insertSegments(ints);
         update = true;
      }

      // Jeśli stan wskazuje na brak przecięć w tym punkcie zdarzeń,
      // to sprawdź, czy odcinki lewy i prawy przecinają się poniżej
      // prostej zamiatającej i odpowiednio uaktualnij kolejkę zdarzeń.
      // W przeciwnym razie, jeśli było przecięcie, porządek odcinków
      // między lewym a prawym został zamieniony, toteż sprawdzamy
      // dwa konkretne przedziały: lewy i jego (nowy) następnik oraz
      // prawy i jego (nowy) poprzednik.
      if (!update) {
         if (left != null && right != null) { updateQueue(left, right); }
      } else {
         if (left != null) { updateQueue(left, lineState.successor(left)); }
         if (right != null) { updateQueue(lineState.pred(right), right); }
      }
   }
   // Dowolne przecięcia poniżej prostej zamiatającej są wstawiane jako
   // punkty zdarzeń
   private void updateQueue (AugmentedNode<ILineSegment> left,
                             AugmentedNode<ILineSegment>  right) {
      // Zbadaj, czy dwa sąsiadujące odcinki się przecinają. Upewnij się,
      // że nowy punkt przecięcia jest *poniżej* prostej zamiatającej
      // i nie jest dodawany powtórnie.
      IPoint p = left.key().intersection(right.key());
      if (p == null) { return; }
      if (EventPoint.pointSorter.compare(p,lineState.sweepPt) > 0) {
         EventPoint new_ep = new EventPoint(p);
         if (!eq.contains(new_ep)) { eq.insert(new_ep); }
      }
   }
}
