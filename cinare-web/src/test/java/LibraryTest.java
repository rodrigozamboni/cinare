import com.cinare.model.Library;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockLib() {
        Library mock = Mockito.mock(Library.class);
        Mockito.when(mock.someLibraryMethodString()).thenReturn("Teste");
        assertThat(mock.someLibraryMethodString()).isEqualTo("Teste");

        Library library = new Library();
        assertThat(library.someLibraryMethodString()).isEqualTo("Ball");
    }
}
