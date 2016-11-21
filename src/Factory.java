/*-----------------------------------------------------------------------------------------------
|	Factory Interface:
|  	All Factories implement this Parameterized simple Factory. Create returns a new instance
|	in this case a Template Object. This offers flexibility.
-------------------------------------------------------------------------------------------------*/

public interface Factory<T> {
	T create(String type);
}
